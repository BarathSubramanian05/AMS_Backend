package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Agency;
import com.janaeswar.AMS.Modal.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
    Optional<Employee> findTopByOrderByEmployeeIdDesc();
    List<Employee> findByAgencyId(String agencyId);
    Optional<Employee> findByPhoneNumber(long phoneNumber);
}
