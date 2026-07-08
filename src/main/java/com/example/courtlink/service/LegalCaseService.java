package com.example.courtlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.courtlink.entity.LegalCaseEntity;
import com.example.courtlink.repository.LegalCaseRepository;

@Service
public class LegalCaseService {

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    
    public LegalCaseEntity saveData(LegalCaseEntity data) {
        return legalCaseRepository.save(data);
    }

    
    public List<LegalCaseEntity> getAllData() {
        return legalCaseRepository.findAll();
    }

 
    public LegalCaseEntity getDataById(Long id) {
        return legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Case Not Found with id: " + id));
    }

    
    public LegalCaseEntity getUserDetails(Long id) {
        return legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Case Not Found"));
    }

    public LegalCaseEntity updateDatabase(Long id, LegalCaseEntity data) {

        LegalCaseEntity viewData = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Case Not Found to Update"));

        viewData.setCaseNumber(data.getCaseNumber());
        viewData.setTitle(data.getTitle());
        viewData.setDescription(data.getDescription());
        viewData.setCourtName(data.getCourtName());
        viewData.setFilingDate(data.getFilingDate());
        viewData.setCaseStatus(data.getCaseStatus());

        return legalCaseRepository.save(viewData);
    }

    public LegalCaseEntity getDelete(Long id) {

        LegalCaseEntity caseData = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Case Not Found to Delete"));

        legalCaseRepository.delete(caseData);

        return caseData;
    }

    
    public LegalCaseEntity patchUser(Long id, LegalCaseEntity data) {

        LegalCaseEntity legalCase = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Case Not Found"));

        if (data.getCaseNumber() != null)
            legalCase.setCaseNumber(data.getCaseNumber());

        if (data.getTitle() != null)
            legalCase.setTitle(data.getTitle());

        if (data.getDescription() != null)
            legalCase.setDescription(data.getDescription());

        if (data.getCourtName() != null)
            legalCase.setCourtName(data.getCourtName());

        if (data.getFilingDate() != null)
            legalCase.setFilingDate(data.getFilingDate());

        if (data.getCaseStatus() != null)
            legalCase.setCaseStatus(data.getCaseStatus());

        return legalCaseRepository.save(legalCase);
    }
}