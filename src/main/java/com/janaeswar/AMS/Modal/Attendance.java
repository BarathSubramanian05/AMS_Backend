package com.janaeswar.AMS.Modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Document
public class Attendance {

    @Id
    private String employeeId;
    private String agencyId;
    private String startTime;
    private String endTime;
    private List<DayAttendance> attendance;
    private HashMap<LocalDate, List<DayAttendance>> attendanceMap;

    public Attendance(String employeeId) {
        this.employeeId = employeeId;
        this.attendance = new ArrayList<>();
    }

    public Attendance(String employeeId, LocalDate d) {
        this.employeeId = employeeId;
        this.attendance = new ArrayList<>();
        this.attendanceMap = new HashMap<>();
        attendanceMap.put(d,new ArrayList<>());
    }

    public Attendance() {
    }

    public Attendance(String employeeId, String startTime, String endTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendance = new ArrayList<>();
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<DayAttendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<DayAttendance> attendance) {
        this.attendance = attendance;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public HashMap<LocalDate, List<DayAttendance>> getAttendanceMap() {
        return attendanceMap;
    }

    public void setAttendanceMap(HashMap<LocalDate, List<DayAttendance>> attendanceMap) {
        this.attendanceMap = attendanceMap;
    }
}

