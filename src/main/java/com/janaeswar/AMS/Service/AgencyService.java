package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Agency;
import com.janaeswar.AMS.Modal.Employee;
import com.janaeswar.AMS.Repository.AgencyRepository;
import com.janaeswar.AMS.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;
private final EmployeeRepository employeeRepository;

    public AgencyService(AgencyRepository agencyRepository, EmployeeRepository employeeRepository) {
        this.agencyRepository = agencyRepository;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> addAgency(Agency agencyRequest) {

        // Check for duplicate agencyName
        Optional<Agency> existingAgency = agencyRepository.findByAgencyName(agencyRequest.getAgencyName());
        if (existingAgency.isPresent()) {
            toggleAgencyStatus(agencyRequest.getAgencyId());
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
        boolean newStatus = !agency.isStatus();
        agency.setStatus(newStatus);
        agencyRepository.save(agency);

        // Cascade toggle to employees of this agenc
        List<Employee> employees = employeeRepository.findByAgencyId(agencyId);
        for (Employee emp : employees) {
            emp.setisActive(!emp.getisActive());
        }
        employeeRepository.saveAll(employees);

        return ResponseEntity.ok("Agency status is: " + (agency.isStatus() ? "active" : "inactive") +
                " | Employees updated to " + (newStatus ? "active" : "inactive"));
    }


    public ResponseEntity<List<Agency>> getAgencies() {
        return new ResponseEntity<>(agencyRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAgency(List<String> agencyIds) {
        List<String> notFoundIds = new ArrayList<>();

        for (String id : agencyIds) {
            Optional<Agency> agencyOpt = agencyRepository.findById(id);
            if (agencyOpt.isEmpty()) {
                notFoundIds.add(id);
            } else {
                toggleAgencyStatus(id);
            }
        }

        if (!notFoundIds.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Some agencies not found: " + String.join(", ", notFoundIds));
        }

        return ResponseEntity.ok("Agencies deleted successfully");
    }

}