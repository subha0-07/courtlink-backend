package com.example.courtlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.courtlink.entity.LegalClientEntity;
import com.example.courtlink.repository.LegalClientRepository;

@Service
public class LegalClientService {

    @Autowired
    private LegalClientRepository legalClientRepository;

  
    public LegalClientEntity saveClient(LegalClientEntity clientData) {
        return legalClientRepository.save(clientData);
    }

    
    public List<LegalClientEntity> getAllClients() {
        return (List<LegalClientEntity>) legalClientRepository.findAll();
    }


    public LegalClientEntity getClientById(Long id) {
        return legalClientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Client Not Found with id: " + id));
    }

  
    public LegalClientEntity updateClient(Long id, LegalClientEntity clientData) {

        LegalClientEntity viewData = legalClientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Client Not Found to Update"));

        viewData.setName(clientData.getName());
        viewData.setEmail(clientData.getEmail());
        viewData.setPhone(clientData.getPhone());
        viewData.setAddress(clientData.getAddress());
        viewData.setCaseType(clientData.getCaseType());
        viewData.setStatus(clientData.getStatus());

        return legalClientRepository.save(viewData);
    }

    public LegalClientEntity deleteClient(Long id) {

        LegalClientEntity clientData = legalClientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Client Not Found to Delete"));

        legalClientRepository.delete(clientData);

        return clientData;
    }

    public LegalClientEntity patchUser(Long id, LegalClientEntity data) {

        LegalClientEntity client = legalClientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Legal Client Not Found"));

        if (data.getName() != null)
            client.setName(data.getName());

        if (data.getEmail() != null)
            client.setEmail(data.getEmail());

        if (data.getPhone() != null)
            client.setPhone(data.getPhone());

        if (data.getAddress() != null)
            client.setAddress(data.getAddress());

        if (data.getCaseType() != null)
            client.setCaseType(data.getCaseType());

        if (data.getStatus() != null)
            client.setStatus(data.getStatus());

        return legalClientRepository.save(client);
    }
}