package by.sinkevich.demo.account.service.service.dto;

import by.sinkevich.demo.account.service.domain.enumeration.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Set;

public class ClientDTO {
    @Schema(defaultValue = "null")
    private Long id;
    private String name;
    private String documentId;
    private DocumentType documentType;
    @Schema(hidden = true)
    private Set<AccountDTO> accounts;

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

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentId='" + documentId + '\'' +
                ", documentType=" + documentType +
                '}';
    }
}
