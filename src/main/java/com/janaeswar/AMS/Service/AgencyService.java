package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Agency;
import com.janaeswar.AMS.Repository.AgencyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public ResponseEntity<?> addAgency(Agency agencyRequest) {

        // Check for duplicate agencyName
        Optional<Agency> existingAgency = agencyRepository.findByAgencyName(agencyRequest.getAgencyName());
        if (existingAgency.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Agency with name '" + agencyRequest.getAgencyName() + "' already exists.");
        }

        // Auto-generate new agencyId
        Optional<Agency> lastAgency = agencyRepository.findTopByOrderByAgencyIdDesc();
        int newAgencyId = 1000; // default starting value
        if (lastAgency.isPresent()) {
            try {
                newAgencyId = Integer.parseInt(lastAgency.get().getAgencyId()) + 1;
            } catch (NumberFormatException e) {
                return ResponseEntity
                        .internalServerError()
                        .body("Failed to parse existing agencyId: " + lastAgency.get().getAgencyId());
            }
        }

        Agency newAgency = new Agency();
        newAgency.setAgencyId(String.valueOf(newAgencyId));
        newAgency.setAgencyName(agencyRequest.getAgencyName());
        newAgency.setAgencyType(agencyRequest.getAgencyType());
        newAgency.setContactPersonName(agencyRequest.getContactPersonName());
        newAgency.setPhoneNumber(agencyRequest.getPhoneNumber());
        newAgency.setEmail(agencyRequest.getEmail());
        newAgency.setAddress(agencyRequest.getAddress());
        newAgency.setPinCode(agencyRequest.getPinCode());
        newAgency.setStatus(agencyRequest.isStatus());

        // Save to DB
        agencyRepository.save(newAgency);

        return ResponseEntity.ok("Agency added successfully.");
    }

    public ResponseEntity<?> updateAgency(Agency agencyRequest) {
        Optional<Agency> existingAgencyOpt = agencyRepository.findById(agencyRequest.getAgencyId());
        if (existingAgencyOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Agency with ID '" + agencyRequest.getAgencyId() + "' not found.");
        }

        // Check for duplicate agencyName (if changed)
        Optional<Agency> duplicateAgencyName = agencyRepository.findByAgencyName(agencyRequest.getAgencyName());
        if (duplicateAgencyName.isPresent() && !duplicateAgencyName.get().getAgencyId().equals(agencyRequest.getAgencyId())) {
            return ResponseEntity
                    .badRequest()
                    .body("Another agency with name '" + agencyRequest.getAgencyName() + "' already exists.");
        }

        // Update existing agency
        Agency agency = existingAgencyOpt.get();
        agency.setAgencyName(agencyRequest.getAgencyName());
        agency.setAgencyType(agencyRequest.getAgencyType());
        agency.setContactPersonName(agencyRequest.getContactPersonName());
        agency.setPhoneNumber(agencyRequest.getPhoneNumber());
        agency.setEmail(agencyRequest.getEmail());
        agency.setAddress(agencyRequest.getAddress());
        agency.setPinCode(agencyRequest.getPinCode());
        agency.setStatus(agencyRequest.isStatus());

        agencyRepository.save(agency);

        return ResponseEntity.ok("Agency updated successfully.");
    }

    public ResponseEntity<?> toggleAgencyStatus(String agencyId) {
        Optional<Agency> agencyOpt = agencyRepository.findById(agencyId);
        if (agencyOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Agency with ID '" + agencyId + "' not found.");
        }

        Agency agency = agencyOpt.get();
        agency.setStatus(!agency.isStatus());
        agencyRepository.save(agency);

        return ResponseEntity.ok("Agency status is: " + (agency.isStatus() ? "active" : "inactive"));
    }

    public ResponseEntity<List<Agency>> getAgencies() {
        return new ResponseEntity<>(agencyRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAgency(String agencyId) {
        Optional<Agency> agencyOpt = agencyRepository.findById(agencyId);
        if (agencyOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Agency with ID '" + agencyId + "' not found.");
        }

        agencyRepository.deleteById(agencyId);
        return ResponseEntity.ok("Agency with ID '" + agencyId + "' deleted successfully.");
    }
}