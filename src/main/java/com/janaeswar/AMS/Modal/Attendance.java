package com.janaeswar.AMS.Modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Attendance {

    @Id
    private String employeeId;
    private List<DayAttendance> attendance;

    public Attendance(String employeeId) {
        this.employeeId = employeeId;
        this.attendance = new ArrayList<>();
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
}

