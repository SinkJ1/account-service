package by.sinkevich.demo.account.service.service.dto;

import by.sinkevich.demo.account.service.domain.enumeration.DocumentType;
import jakarta.persistence.*;

public class ClientDTO {
    private Long id;
    private String name;
    private AccountDTO account;
    private String documentId;
    private DocumentType documentType;

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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", documentId='" + documentId + '\'' +
                ", documentType=" + documentType +
                '}';
    }
}
