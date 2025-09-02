package com.janaeswar.AMS.Modal;

import java.time.LocalDateTime;

public class DayAttendance {
    private LocalDateTime inTime;
    private LocalDateTime outTime;

    public DayAttendance(LocalDateTime inTime, LocalDateTime outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }

    // Getters and setters

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
