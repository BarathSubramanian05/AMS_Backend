package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Attendance;
import com.janaeswar.AMS.Modal.DayAttendance;
import com.janaeswar.AMS.Repository.AttendanceRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final RequestService requestService;

    public AttendanceService(AttendanceRepository attendanceRepository, RequestService requestService) {
        this.attendanceRepository = attendanceRepository;
        this.requestService = requestService;
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
                .orElse(new Attendance(employeeId,inTime.toLocalDate()));
//        for(DayAttendance att : record.getAttendance())
//        {
//            if(att.getInTime().toLocalDate() == inTime.toLocalDate())
//            {
//                return ResponseEntity.ok("Already Logged In");
//            }
//        }
        DayAttendance obj = new DayAttendance(inTime, null);
        record.getAttendance().add(obj);
        if(record.getAttendanceMap().containsKey(inTime.toLocalDate()))
        {
            HashMap<LocalDate,List<DayAttendance>> map = record.getAttendanceMap();
            List<DayAttendance> d = map.get(inTime.toLocalDate());
            d.add(obj);
            record.setAttendanceMap(map);
        }

        attendanceRepository.save(record);
        System.out.println(record.getAttendanceMap());
        return ResponseEntity.ok("In-time added");
    }

//    public boolean checkExistance(LocalDateTime inTime Attendance record)
//    {
//
//    }

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
                List<DayAttendance> d = record.getAttendanceMap().get(outTime.toLocalDate());
                d.getLast().setOutTime(outTime);
                attendanceRepository.save(record);
                return ResponseEntity.ok("Out-time updated");
            }
        }
        return ResponseEntity.badRequest().body("No open in-time found to update out-time");
    }

    public ResponseEntity<?> addDate(@RequestParam String employeeId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Attendance attendance = attendanceRepository.findById(employeeId)
                .orElse(new Attendance(employeeId));

        // Create new DayAttendance with null times
        DayAttendance newDay = new DayAttendance(date,null, null);
        attendance.getAttendance().add(newDay);

        attendanceRepository.save(attendance);
        requestService.deleteRequest(employeeId,date);
        return ResponseEntity.ok("Attendance record created for " + employeeId + " on " + date);
    }
}