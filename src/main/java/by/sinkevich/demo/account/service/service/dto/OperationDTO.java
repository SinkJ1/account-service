package by.sinkevich.demo.account.service.service.dto;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class OperationDTO {
    private Long id;
    private OperationType type;
    @Positive
    private BigDecimal amount;
    private AccountDTO account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OperationDTO{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
