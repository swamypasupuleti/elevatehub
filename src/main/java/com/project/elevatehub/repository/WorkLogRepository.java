package com.project.elevatehub.repository;


import com.project.elevatehub.model.SupportScore;
import com.project.elevatehub.model.Worklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface WorkLogRepository extends JpaRepository<Worklog, Long> {

    @Query("select s from SupportScore s where s.email = :email")
    SupportScore findByEmail(@Param(value = "email") String email);

}
