package by.sinkevich.demo.account.service.config;

import by.sinkevich.demo.account.service.domain.Account;
import by.sinkevich.demo.account.service.domain.enumeration.DocumentType;
import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import by.sinkevich.demo.account.service.repository.AccountRepository;
import by.sinkevich.demo.account.service.service.ClientService;
import by.sinkevich.demo.account.service.service.OperationService;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OperationServiceImplTest {

    @Autowired
    private OperationService operationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountRepository accountRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:11"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void increaseAmountConcurrently_tenThreads_expectedResult() throws InterruptedException {
        final BigDecimal EXPECTED_RESULT = new BigDecimal("20.00");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Client");
        clientDTO.setDocumentId("docId");
        clientDTO.setDocumentType(DocumentType.PASSPORT);

        clientDTO = clientService.save(clientDTO);

        Assertions.assertEquals(clientDTO.getAccounts().size(), 1);

        AccountDTO accountDTO = clientDTO.getAccounts().stream().findAny().get();

        int threadCount = 10;

        CountDownLatch latch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int index = 0; index < threadCount; index++) {
            OperationDTO operationDTO = new OperationDTO();
            operationDTO.setAmount(BigDecimal.TWO);
            operationDTO.setType(OperationType.INCOME);
            operationDTO.setAccount(accountDTO);

            executorService.submit(() -> {
                try {
                    operationService.save(operationDTO);
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        Account account = accountRepository.findById(accountDTO.getId()).get();

        Assertions.assertEquals(EXPECTED_RESULT, account.getAmount());

    }

    @Test
    void decreaseAmountConcurrently_tenThreads_expectedResult() throws InterruptedException {
        final BigDecimal EXPECTED_RESULT = new BigDecimal("-1221.20");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Client");
        clientDTO.setDocumentId("docId");
        clientDTO.setDocumentType(DocumentType.PASSPORT);

        clientDTO = clientService.save(clientDTO);

        Assertions.assertEquals(clientDTO.getAccounts().size(), 1);

        AccountDTO accountDTO = clientDTO.getAccounts().stream().findAny().get();

        int threadCount = 10;

        CountDownLatch latch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int index = 0; index < threadCount; index++) {
            OperationDTO operationDTO = new OperationDTO();
            operationDTO.setAmount(new BigDecimal("122.12"));
            operationDTO.setType(OperationType.EXPENSE);
            operationDTO.setAccount(accountDTO);

            executorService.submit(() -> {
                try {
                    operationService.save(operationDTO);
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        Account account = accountRepository.findById(accountDTO.getId()).get();

        Assertions.assertEquals(EXPECTED_RESULT, account.getAmount());

    }

    @Test
    void increaseAndDecreaseAmountConcurrently_tenThreads_expectedResult() throws InterruptedException {
        final BigDecimal EXPECTED_RESULT = new BigDecimal("-39.60");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Client");
        clientDTO.setDocumentId("docId");
        clientDTO.setDocumentType(DocumentType.PASSPORT);

        clientDTO = clientService.save(clientDTO);

        Assertions.assertEquals(clientDTO.getAccounts().size(), 1);

        AccountDTO accountDTO = clientDTO.getAccounts().stream().findAny().get();

        // result is -99.90
        changeAmount(accountDTO, OperationType.EXPENSE, 2.22);
        // result is 60.30
        changeAmount(accountDTO, OperationType.INCOME, 1.34);

        Account account = accountRepository.findById(accountDTO.getId()).get();

        Assertions.assertEquals(EXPECTED_RESULT, account.getAmount());

    }

    @Test
    void increaseAndDecreaseAmountConcurrently_tenThreads_notExpectedResult() throws InterruptedException {
        final BigDecimal EXPECTED_RESULT = new BigDecimal("0.0");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Client");
        clientDTO.setDocumentId("docId");
        clientDTO.setDocumentType(DocumentType.PASSPORT);

        clientDTO = clientService.save(clientDTO);

        Assertions.assertEquals(clientDTO.getAccounts().size(), 1);

        AccountDTO accountDTO = clientDTO.getAccounts().stream().findAny().get();

        // result is -99.90
        changeAmount(accountDTO, OperationType.EXPENSE, 2.22);
        // result is 60.30
        changeAmount(accountDTO, OperationType.INCOME, 1.34);

        Account account = accountRepository.findById(accountDTO.getId()).get();

        Assertions.assertNotEquals(EXPECTED_RESULT, account.getAmount());

    }

    private void changeAmount(AccountDTO accountDTO, OperationType type, double amount) throws InterruptedException {
        int threadCount = 10;

        CountDownLatch latch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int index = 0; index < threadCount; index++) {
            OperationDTO operationDTO = new OperationDTO();
            operationDTO.setAmount(new BigDecimal(amount * index));
            operationDTO.setType(type);
            operationDTO.setAccount(accountDTO);

            executorService.submit(() -> {
                try {
                    operationService.save(operationDTO);
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }

}
