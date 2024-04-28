package by.sinkevich.demo.account.service.service;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {

    void changeAmount(AccountDTO accountDTO, BigDecimal amount, OperationType type);

}
