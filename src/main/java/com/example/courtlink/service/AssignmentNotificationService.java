package com.example.courtlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.courtlink.entity.AssignmentNotificationEntity;
import com.example.courtlink.repository.AssignmentNotificationRepository;

@Service
public class AssignmentNotificationService {

    @Autowired
    private AssignmentNotificationRepository assignmentNotificationRepository;

    
    public AssignmentNotificationEntity save(AssignmentNotificationEntity notification) {
        return assignmentNotificationRepository.save(notification);
    }

    public List<AssignmentNotificationEntity> getAll() {
        return assignmentNotificationRepository.findAll();
    }

  
    public AssignmentNotificationEntity getDataById(Long id) {
        return assignmentNotificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Assignment Notification Not Found with id: " + id));
    }

   
    public AssignmentNotificationEntity updateData(Long id, AssignmentNotificationEntity notification) {

        AssignmentNotificationEntity assignment = assignmentNotificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Assignment Notification Not Found"));

        assignment.setCaseName(notification.getCaseName());
        assignment.setFileName(notification.getFileName());
        assignment.setFileType(notification.getFileType());
        assignment.setStorageUrl(notification.getStorageUrl());
        assignment.setUploadDate(notification.getUploadDate());
        assignment.setLegalCaseId(notification.getLegalCaseId());

        return assignmentNotificationRepository.save(assignment);
    }

   
    public AssignmentNotificationEntity getDelete(Long id) {

        AssignmentNotificationEntity assignment = assignmentNotificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Assignment Notification Not Found"));

        assignmentNotificationRepository.delete(assignment);

        return assignment;
    }

   
    public AssignmentNotificationEntity patchUser(Long id, AssignmentNotificationEntity data) {

        AssignmentNotificationEntity assignment = assignmentNotificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Assignment Notification Not Found"));

        if (data.getCaseName() != null)
            assignment.setCaseName(data.getCaseName());

        if (data.getFileName() != null)
            assignment.setFileName(data.getFileName());

        if (data.getFileType() != null)
            assignment.setFileType(data.getFileType());

        if (data.getStorageUrl() != null)
            assignment.setStorageUrl(data.getStorageUrl());

        if (data.getUploadDate() != null)
            assignment.setUploadDate(data.getUploadDate());

        if (data.getLegalCaseId() != 0)
            assignment.setLegalCaseId(data.getLegalCaseId());

        return assignmentNotificationRepository.save(assignment);
    }
}