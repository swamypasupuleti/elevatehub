package com.project.elevatehub.repository;


import com.project.elevatehub.model.SupportScore;
import com.project.elevatehub.model.Worklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface WorkLogRepository extends JpaRepository<Worklog, Long> {

    @Query("select s from SupportScore s where s.email = :email")
    SupportScore findByEmail(@Param(value = "email") String email);

    @Query("FROM Worklog w WHERE MONTH(:employeeId) = MONTH(CURRENT_DATE()) AND YEAR(:employeeId) = YEAR(CURRENT_DATE())")
    List<Object> getCurrentMonthWorkLog(@Param(value = "employeeId") String employeeId);

//    @Query("SELECT w from Worklog w WHERE w.createdAt >= dateadd(month, -1, GETDATE()) AND w.createdAt < GETDATE()")
//    List<Object> getPreviousMonthWorkLog(@Param(value = "created_at") Date createdAt);
//
//    @Query("SELECT w from Worklog w where w.createdAt >= dateadd(day, 1 - day(dateadd(month, -12, getdate())), dateadd(month, -12, getdate()))")
//    List<Object> getPreviousYearWorkLog(@Param(value = "created_at") Date createdAt);
//

}
