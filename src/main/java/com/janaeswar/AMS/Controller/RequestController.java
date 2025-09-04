package com.janaeswar.AMS.Controller;

import com.janaeswar.AMS.Modal.Request;
import com.janaeswar.AMS.Service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    @GetMapping("/")
    public ResponseEntity<?> getRequests(){
        return requestService.getRequests();
    }
    @PostMapping("/addrequest")
    public ResponseEntity<?> addRequests(@RequestBody Request request){
        return requestService.addRequest(request);
    }

    @PutMapping("/toggle")
    public ResponseEntity<?> toggleApproved(@RequestParam String employeeId,
                                            @RequestParam String date) {
        // Convert date string to LocalDate
        LocalDate parsedDate = LocalDate.parse(date);
        return requestService.toggleApproved(employeeId, parsedDate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRequest(@RequestParam String employeeId,
                                            @RequestParam String date) {
        // Convert date string to LocalDate
        LocalDate parsedDate = LocalDate.parse(date);
        System.out.println(employeeId+" "+date);
        return requestService.deleteRequest(employeeId, parsedDate);
    }
}
