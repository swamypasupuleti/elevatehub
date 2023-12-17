package com.project.elevatehub.repository;

import com.project.elevatehub.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.employeeId = :employeeId and u.password = :password")
    Users findByUserNameAndPassword(@Param(value = "employeeId") String employeeId,
                                    @Param(value = "password") String password);




}
