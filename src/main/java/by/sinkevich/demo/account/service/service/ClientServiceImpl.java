package by.sinkevich.demo.account.service.service;

import by.sinkevich.demo.account.service.domain.Account;
import by.sinkevich.demo.account.service.domain.Client;
import by.sinkevich.demo.account.service.domain.Currency;
import by.sinkevich.demo.account.service.repository.AccountRepository;
import by.sinkevich.demo.account.service.repository.ClientRepository;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import by.sinkevich.demo.account.service.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Long DEFAULT_CURRENCY_ID = 1L;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AccountRepository accountRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);

        Currency currency = new Currency();
        currency.setId(DEFAULT_CURRENCY_ID);

        client.setAccounts(Set.of(createAccount(client, currency)));
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    private Account createAccount(Client client, Currency currency) {
        Account account = new Account();
        account.setAmount(new BigDecimal("0.00"));
        account.setCurrency(currency);
        account.setClient(client);
        return account;
    }

}
