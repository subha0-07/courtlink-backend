package com.example.courtlink.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class LegalDocumentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "File name cannot be blank")
    @Size(min = 3, max = 100, message = "File name must be between 3 and 100 characters")
    private String fileName;

    @NotBlank(message = "File type cannot be blank")
    @Size(min = 2, max = 10, message = "File type must be between 2 and 10 characters (e.g., pdf, docx)")
    private String fileType;

    @NotBlank(message = "Storage URL cannot be blank")
    @Size(min = 10, max = 500, message = "Storage URL length must be between 10 and 500 characters")
    private String storageUrl;

    private LocalDateTime uploadDate;

    public LegalDocumentEntity(LocalDateTime uploadDate, String fileName, String fileType, Long id, String storageUrl) {
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this.fileType = fileType;
        this.id = id;
        this.storageUrl = storageUrl;
    }

    public LegalDocumentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
