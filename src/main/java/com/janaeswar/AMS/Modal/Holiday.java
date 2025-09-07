package com.janaeswar.AMS.Modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Holiday {
    @Id
    LocalDate date;
    String description;
    List<Integer> agencyId;

    public Holiday(LocalDate date, String description, List<Integer> agencyId) {
        this.date = date;
        this.description = description;
        this.agencyId = agencyId;
    }
    public Holiday() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(List<Integer> agencyId) {
        this.agencyId = agencyId;
    }
}
