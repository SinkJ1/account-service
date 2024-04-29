package by.sinkevich.demo.account.service.service.impl;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import by.sinkevich.demo.account.service.repository.AccountRepository;
import by.sinkevich.demo.account.service.service.AccountService;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void changeAmount(AccountDTO accountDTO, @Positive BigDecimal amount, OperationType type) {
        accountRepository.findOneById(accountDTO.getId()).ifPresent(account -> {
            if (type.name().equals(OperationType.INCOME.name())) {
                account.setAmount(account.getAmount().add(amount));
            } else {
                account.setAmount(account.getAmount().subtract(amount));
            }
            accountRepository.save(account);
        });
    }
}
