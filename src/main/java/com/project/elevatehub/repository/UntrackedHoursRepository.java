package com.project.elevatehub.repository;


import java.util.List;

import com.project.elevatehub.model.Untrackedhours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UntrackedHoursRepository extends JpaRepository<Untrackedhours, Long> {

    @Query(value = "SELECT CONCAT(YEAR(u.created_at), '/', WEEK(u.created_at)) AS week_name, sum(u.timespent) as timespend FROM untrackedhours u WHERE (:employeeId is null or u.employee_id =:employeeId) and u.created_at > (NOW() - INTERVAL :inter DAY) GROUP BY week_name", nativeQuery = true)
    List<Object> getUntrackedHoursByInterval(@Param(value = "employeeId") String employeeId, Integer inter);

}
