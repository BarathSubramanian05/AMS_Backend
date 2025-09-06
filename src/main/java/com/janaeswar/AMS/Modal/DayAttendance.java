package com.janaeswar.AMS.Modal;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class DayAttendance {
    private LocalDate date;
    private LocalDateTime inTime;
    private LocalDateTime outTime;

    public DayAttendance() {
    }

    public DayAttendance(LocalDateTime inTime, LocalDateTime outTime) {
        this.inTime = inTime;
        addDate(inTime);
        this.outTime = outTime;
    }
    public DayAttendance(LocalDate date,LocalDateTime inTime, LocalDateTime outTime) {
        this.date=date;
        this.inTime = inTime;
        this.outTime = outTime;
    }



    public void addDate(LocalDateTime inTime)
    {
        this.date = inTime.toLocalDate();
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }
}
