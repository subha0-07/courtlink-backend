package com.example.courtlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.courtlink.entity.LegalDocumentEntity;
import com.example.courtlink.repository.LegalDocumentRepository;

@Service
public class LegalDocumentService {

    @Autowired
    private LegalDocumentRepository legalDocumentRepository;

    
    public LegalDocumentEntity saveCase(LegalDocumentEntity caseData) {
        return legalDocumentRepository.save(caseData);
    }

    
    public List<LegalDocumentEntity> getAllCases() {
        return legalDocumentRepository.findAll();
    }

    
    public LegalDocumentEntity getCaseById(Long id) {
        return legalDocumentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Document Not Found with id: " + id));
    }

    
    public LegalDocumentEntity updateCase(Long id, LegalDocumentEntity caseData) {

        LegalDocumentEntity viewData = legalDocumentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Document Not Found to Update"));

        viewData.setId(caseData.getId());
        viewData.setFileName(caseData.getFileName());
        viewData.setFileType(caseData.getFileType());
        viewData.setStorageUrl(caseData.getStorageUrl());
        viewData.setUploadDate(caseData.getUploadDate());

        return legalDocumentRepository.save(viewData);
    }

    
    public LegalDocumentEntity deleteCase(Long id) {

        LegalDocumentEntity caseData = legalDocumentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Document Not Found to Delete"));

        legalDocumentRepository.delete(caseData);

        return caseData;
    }

    
    public LegalDocumentEntity patchUser(Long id, LegalDocumentEntity data) {

        LegalDocumentEntity document = legalDocumentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Document Not Found"));

        if (data.getFileName() != null)
            document.setFileName(data.getFileName());

        if (data.getFileType() != null)
            document.setFileType(data.getFileType());

        if (data.getStorageUrl() != null)
            document.setStorageUrl(data.getStorageUrl());

        if (data.getUploadDate() != null)
            document.setUploadDate(data.getUploadDate());

        return legalDocumentRepository.save(document);
    }
}