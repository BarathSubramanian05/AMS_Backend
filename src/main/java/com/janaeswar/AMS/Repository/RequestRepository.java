package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RequestRepository extends MongoRepository<Request,String> {
    Optional<Request> findByEmployeeIdAndDate(String employeeId, LocalDate date);
}
