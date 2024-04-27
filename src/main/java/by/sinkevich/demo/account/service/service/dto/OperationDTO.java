package by.sinkevich.demo.account.service.service.dto;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;

public class OperationDTO {
    private Long id;
    private OperationType type;
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

    @Override
    public String toString() {
        return "OperationDTO{" +
                "id=" + id +
                ", type=" + type +
                ", account=" + account +
                '}';
    }
}
