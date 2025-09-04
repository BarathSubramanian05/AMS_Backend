package com.janaeswar.AMS.Modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Document
public class Request {
    @Id
    private String id;
    private String employeeId;
    private LocalDate date;
    private String reason;

    public Request(String id, String employeeId, LocalDate date) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
