package by.sinkevich.demo.account.service.service.impl;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import by.sinkevich.demo.account.service.repository.AccountRepository;
import by.sinkevich.demo.account.service.service.AccountService;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void changeAmount(AccountDTO accountDTO, @Positive BigDecimal amount, OperationType type) {
        accountRepository.findOneById(accountDTO.getId()).ifPresent(account -> {
            log.debug("Change amount of account - {}, amount - {}, operationType - {}", account.getId(), amount, type.name());
            if (type.name().equals(OperationType.INCOME.name())) {
                account.setAmount(account.getAmount().add(amount));
            } else {
                account.setAmount(account.getAmount().subtract(amount));
            }
            accountRepository.save(account);
        });
    }
}
