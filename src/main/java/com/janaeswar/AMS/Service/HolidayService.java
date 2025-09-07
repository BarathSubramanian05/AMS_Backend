package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Holiday;
import com.janaeswar.AMS.Repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }
    public Holiday createHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public Optional<Holiday> getHolidayByDate(LocalDate date) {
        return holidayRepository.findById(date);
    }

    public Holiday updateHoliday(LocalDate date, Holiday updatedHoliday) {
        return holidayRepository.findById(date)
                .map(existing -> {
                    existing.setDescription(updatedHoliday.getDescription());
                    existing.setAgencyId(updatedHoliday.getAgencyId());
                    return holidayRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Holiday not found for date: " + date));
    }

    public void deleteHoliday(LocalDate date) {
        holidayRepository.deleteById(date);
    }
}
