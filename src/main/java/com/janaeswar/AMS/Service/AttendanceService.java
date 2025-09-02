package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Attendance;
import com.janaeswar.AMS.Modal.DayAttendance;
import com.janaeswar.AMS.Repository.AttendanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    public ResponseEntity<?> getAttendance(String employeeId) {
        Optional<Attendance> record = attendanceRepository.findById(employeeId);

        if (record.isPresent()) {
            return ResponseEntity.ok(record.get());
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("no data available");
        }
    }

    public ResponseEntity<?> addInTime(String employeeId, LocalDateTime inTime) {
        Attendance record = attendanceRepository.findByEmployeeId(employeeId)
                .orElse(new Attendance(employeeId));

        record.getAttendance().add(new DayAttendance(inTime, null));
        attendanceRepository.save(record);
        return ResponseEntity.ok("In-time added");
    }

    public ResponseEntity<?> addOutTime(String employeeId, LocalDateTime outTime) {
        Attendance record = attendanceRepository.findByEmployeeId(employeeId)
                .orElse(null);

        if (record == null || record.getAttendance().isEmpty()) {
            return ResponseEntity.badRequest().body("No in-time record found for out-time");
        }

        // Find the last record with null outTime
        for (int i = record.getAttendance().size() - 1; i >= 0; i--) {
            DayAttendance entry = record.getAttendance().get(i);
            if (entry.getOutTime() == null) {
                entry.setOutTime(outTime);
                attendanceRepository.save(record);
                return ResponseEntity.ok("Out-time updated");
            }
        }

        return ResponseEntity.badRequest().body("No open in-time found to update out-time");
    }
}
