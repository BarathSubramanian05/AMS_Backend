package com.janaeswar.AMS.Controller;

import com.janaeswar.AMS.Modal.Agency;
import com.janaeswar.AMS.Service.AgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agency")
@CrossOrigin(origins = "http://localhost:3000")
public class AgencyController {
    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAgencies() {
        return agencyService.getAgencies();
    }

    @PostMapping("/addagency")
    public ResponseEntity<?> addAgency(@RequestBody Agency agency) {
        return agencyService.addAgency(agency);
    }

    @PutMapping("/updateagency")
    public ResponseEntity<?> updateAgency(@RequestBody Agency agency) {
        return agencyService.updateAgency(agency);
    }

    @PutMapping("/toggleagencystatus/{agencyId}")
    public ResponseEntity<?> toggleAgencyStatus(@PathVariable String agencyId) {
        return agencyService.toggleAgencyStatus(agencyId);
    }

    @DeleteMapping("/deleteagencies")
    public ResponseEntity<?> deleteAgency(@RequestBody List<String> agencyIds) {
        return agencyService.deleteAgency(agencyIds);
    }

}