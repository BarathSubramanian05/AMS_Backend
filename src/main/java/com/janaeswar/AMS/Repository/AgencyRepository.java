package com.janaeswar.AMS.Repository;

import com.janaeswar.AMS.Modal.Agency;
import com.janaeswar.AMS.Modal.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgencyRepository extends MongoRepository<Agency,Object> {
    Optional<Agency> findByAgencyName(String agencyName);

    Optional<Agency> findTopByOrderByAgencyIdDesc();

}
