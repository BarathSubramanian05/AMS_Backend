package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<Attendance,String> {
    Optional<Attendance> findByEmployeeId(String employeeId);
}
