package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface HolidayRepository extends MongoRepository<Holiday, LocalDate> {
}
