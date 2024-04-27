package by.sinkevich.demo.account.service.service.dto;

import jakarta.persistence.*;

public class CurrencyDTO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
