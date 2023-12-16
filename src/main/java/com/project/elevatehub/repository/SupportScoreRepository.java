package com.project.elevatehub.repository;


import com.project.elevatehub.model.entities.SupportScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SupportScoreRepository extends JpaRepository<SupportScore, Long> {

    @Query("select s from SupportScore s where s.email = :email")
    SupportScore findByEmail(@Param(value = "email") String email);

}