package by.sinkevich.demo.account.service.domain;

import by.sinkevich.demo.account.service.domain.enumeration.OperationType;
import jakarta.persistence.*;

@Table
@Entity(name = "operation")
public class Operation {

    @Id
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne(optional = false)
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", type=" + type +
                ", account=" + account +
                '}';
    }
}
