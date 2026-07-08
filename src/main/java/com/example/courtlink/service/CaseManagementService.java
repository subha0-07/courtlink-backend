package com.example.courtlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.courtlink.entity.CaseHearingEntity;
import com.example.courtlink.repository.CaseHearingRepository;

@Service
public class CaseManagementService {

    @Autowired
    private CaseHearingRepository caseHearingRepository;

    public CaseHearingEntity saveData(CaseHearingEntity data) {
        return caseHearingRepository.save(data);
    }

    public List<CaseHearingEntity> getAllData() {
        return (List<CaseHearingEntity>) caseHearingRepository.findAll();
    }

    public CaseHearingEntity getDataById(Long id) {
        return caseHearingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Case Hearing Not Found with id: " + id));
    }

    public CaseHearingEntity getUserDetails(Long id) {
        return caseHearingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Case Hearing Not Found"));
    }

    public CaseHearingEntity updateDatabase(Long id, CaseHearingEntity data) {

        CaseHearingEntity viewData = caseHearingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Case Hearing Not Found to Update"));

        viewData.setCaseNumber(data.getCaseNumber());
        viewData.setHearingDate(data.getHearingDate());
        viewData.setJudgeName(data.getJudgeName());
        viewData.setCourtRoom(data.getCourtRoom());
        viewData.setStatus(data.getStatus());
        viewData.setRemarks(data.getRemarks());

        return caseHearingRepository.save(viewData);
    }

    public CaseHearingEntity getDelete(Long id) {

        CaseHearingEntity hearingData = caseHearingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Case Hearing Not Found to Delete"));

        caseHearingRepository.delete(hearingData);
        return hearingData;
    }
     public CaseHearingEntity patchUser(Long id, CaseHearingEntity data) {

        CaseHearingEntity hearing = caseHearingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Case Hearing Not Found"));

        if (data.getCaseNumber() != null)
            hearing.setCaseNumber(data.getCaseNumber());

        if (data.getHearingDate() != null)
            hearing.setHearingDate(data.getHearingDate());

        if (data.getJudgeName() != null)
            hearing.setJudgeName(data.getJudgeName());

        if (data.getCourtRoom() != null)
            hearing.setCourtRoom(data.getCourtRoom());

        if (data.getStatus() != null)
            hearing.setStatus(data.getStatus());

        if (data.getRemarks() != null)
            hearing.setRemarks(data.getRemarks());

        return caseHearingRepository.save(hearing);
    }
}