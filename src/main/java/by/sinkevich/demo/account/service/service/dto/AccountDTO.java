package by.sinkevich.demo.account.service.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
public class AccountDTO {
    @Schema(defaultValue = "null")
    private Long id;
    private ClientDTO client;
    private BigDecimal amount;
    private CurrencyDTO currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", client=" + client +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
