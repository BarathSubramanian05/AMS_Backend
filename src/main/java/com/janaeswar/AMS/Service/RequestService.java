package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Request;
import com.janaeswar.AMS.Repository.RequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public ResponseEntity<?> getRequests() {
        List<Request> requestList = requestRepository.findAll();
        if(requestList.isEmpty()){
            return ResponseEntity.badRequest().body("No requests found");
        }
        return ResponseEntity.ok(requestList);
    }

    public ResponseEntity<?> addRequest(Request request) {
        return ResponseEntity.ok(requestRepository.save(request));
    }

    public ResponseEntity<?> toggleApproved(String employeeId, LocalDate date) {
        Optional<Request> existingRequest = requestRepository.findByEmployeeIdAndDate(employeeId, date);

        if (existingRequest.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Request not found for employeeId: " + employeeId + " on date: " + date);
        }

        Request request = existingRequest.get();
        request.setApproved(!request.isApproved());  // Toggle the boolean
        requestRepository.save(request);

        return ResponseEntity.ok(request);
    }
}
