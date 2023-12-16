package com.project.elevatehub.repository;

import com.project.elevatehub.exception.BusinessException;
import com.project.elevatehub.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Query("update Users u set u.companyName = :val where u.id = :id")
    void updateCompanyName(@Param(value = "id") Long userId, @Param(value = "val") String companyName);

    @Modifying
    @Query("update Users u set u.email = :val where u.id = :id")
    void updateEmail(@Param(value = "id") Long userId, @Param(value = "val") String email);

    @Modifying
    @Query("update Users u set u.name = :val where u.id = :id")
    void updateName(@Param(value = "id") Long userId, @Param(value = "val") String name);

    @Modifying
    @Query("update Users u set u.phoneNumber = :val where u.id = :id")
    void updatePhoneNumber(@Param(value = "id") Long userId, @Param(value = "val") String phoneNumber);
	
	@Modifying
	@Query("update Users u set u.rewardPoints = :reward_points where u.id = :user_id")
	Integer updateRewardPointsById(@Param("reward_points") Integer rewardPoints, @Param("user_id") Long userId);

    @Modifying
    @Query("update Users u set u.aadharNumber = :val where u.id = :id")
    void updateAadharNumber(@Param(value = "id") Long userId, @Param(value = "val") String aadharNumber);


    boolean existsByPhoneNumber(String phoneNumber);

    Users findUsersByPhoneNumber(String phoneNumber) throws BusinessException;
}
