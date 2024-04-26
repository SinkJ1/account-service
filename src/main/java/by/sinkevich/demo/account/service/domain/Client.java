package by.sinkevich.demo.account.service.domain;

import by.sinkevich.demo.account.service.domain.enumeration.DocumentType;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String documentId;
    @Column
    @Enumerated(EnumType.STRING)
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
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentId='" + documentId + '\'' +
                ", documentType=" + documentType +
                '}';
    }
}
