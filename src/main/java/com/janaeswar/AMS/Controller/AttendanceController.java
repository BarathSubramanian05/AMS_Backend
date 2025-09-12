package com.janaeswar.AMS.Controller;
import com.janaeswar.AMS.Service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getAttendance(@PathVariable String employeeId){
        return attendanceService.getAttendance(employeeId);
    }

    @PostMapping("/addintime")
    public ResponseEntity<?> addInTime(@RequestParam String employeeId,@RequestParam String agencyId,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inTime) {
        System.out.println(employeeId+" "+inTime);
        return attendanceService.addInTime(employeeId, agencyId,inTime);
    }

    @PostMapping("/addouttime")
    public ResponseEntity<?> addOutTime(@RequestParam String employeeId,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime outTime) {
        return attendanceService.addOutTime(employeeId, outTime);
    }

    @PostMapping("/addDate")
    public ResponseEntity<?> addDate(@RequestParam String employeeId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
            return attendanceService.addDate(employeeId,date);
    }
    @GetMapping("/getpresentemployeesbyagencyid/{agencyId}")
    public ResponseEntity<?> getPresentEmployeesByAgencyId(@PathVariable String agencyId){
        return attendanceService.getPresentEmployeesByAgencyId(agencyId,LocalDate.now());
    }
}
