package com.project.elevatehub.repository;

import com.project.elevatehub.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.employeeId = :employeeId")
    Users findByUserName(@Param(value = "employeeId") String employeeId);

}
