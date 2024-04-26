package by.sinkevich.demo.account.service.domain;

import jakarta.persistence.*;

@Table
@Entity(name = "account")
public class Account {

    @Id
    private Long id;

    @ManyToOne(optional = false)
    private Client client;
    private Double amount;
    @ManyToOne(optional = false)
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
