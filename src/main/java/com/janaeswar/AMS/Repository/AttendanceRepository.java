package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Attendance;
import com.janaeswar.AMS.Modal.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<Attendance,String> {
    Optional<Attendance> findByEmployeeId(String employeeId);

}
