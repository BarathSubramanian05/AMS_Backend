package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Request;
import com.janaeswar.AMS.Repository.AttendanceRepository;
import com.janaeswar.AMS.Repository.RequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final AttendanceRepository attendanceRepository;

    public RequestService(RequestRepository requestRepository, AttendanceRepository attendanceRepository) {
        this.requestRepository = requestRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public ResponseEntity<?> getRequests() {
        List<Request> requestList = requestRepository.findAll();
        return ResponseEntity.ok(requestList);
    }

    public ResponseEntity<?> addRequest(Request request) {
        return ResponseEntity.ok(requestRepository.save(request));
    }

    public ResponseEntity<?> toggleApproved(String employeeId, LocalDate date) {
        // First check if request exists
        Optional<Request> reqOpt = requestRepository.findByEmployeeIdAndDate(employeeId, date);
        if (reqOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "employeeId", employeeId,
                    "date", date
            ));
        }

        // Then check attendance for employeeId + date
        boolean exists = attendanceRepository.findByEmployeeId(employeeId)
                .map(att -> att.getAttendance().stream()
                        .anyMatch(d -> d.getInTime() != null &&
                                d.getInTime().toLocalDate().equals(date)))
                .orElse(false);
        System.out.println("Success");
        if (exists) {
            System.out.println("true");
            return ResponseEntity.ok("Attendance already exists for " + employeeId + " on " + date);
        } else {
            System.out.println("false");
            return ResponseEntity.status(404).body(Map.of(
                    "employeeId", employeeId,
                    "date", date
            ));

        }

    }


    public ResponseEntity<?> deleteRequest(String empid,LocalDate date)
    {
        Request r =  requestRepository.findFirstByEmployeeIdAndDateOrderByIdDesc(empid,date).orElse(null);
        System.out.println(r);
        if(r!=null) {
            requestRepository.deleteById(r.getId());
        }
        System.out.println(r);
        return ResponseEntity.ok("Deleted");
    }


}
