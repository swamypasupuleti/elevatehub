package com.project.elevatehub.repository;


import com.project.elevatehub.model.Timesheet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TerRepository extends JpaRepository<Timesheet, Long> {

    @Query(value = "SELECT CONCAT(YEAR(w.created_at), '/', WEEK(w.created_at)) AS week_name, sum(w.workhours) as timespend FROM ter w WHERE (:employeeId is null or w.employee_id =:employeeId) and (:projectCode is null or w.project=:projectCode) and w.created_at > (NOW() - INTERVAL :inter DAY) GROUP BY week_name", nativeQuery = true)
    List<Object> getWorkLogByInterval(@Param(value = "employeeId") String employeeId, @Param(value = "projectCode") String projectCode, Integer inter);

}
