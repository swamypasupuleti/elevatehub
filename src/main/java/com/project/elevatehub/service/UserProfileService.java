package com.project.elevatehub.service;

import com.project.elevatehub.exception.BusinessException;
import com.project.elevatehub.model.entities.Users;

import javassist.NotFoundException;

public interface UserProfileService {

    /**
     * Updates specific user details like name, email, company name, profile image if provided
     * @param userId
     * @param newUser
     * @return
     */
    void updateUserDetails(Long userId, Users newUser);

    /**
     * Adds default data to user while creation
     * @param user
     */
    void enrichUserData(Users user);

    /**
     * Updates phone Number in DB for a given user id
     * @param userId
     * @param phoneNumber
     */
    void updatePhoneNumber(Long userId, String phoneNumber);

    Users findUserByPhoneNumber(String phoneNumber) throws BusinessException;

    Integer updateRewardPointsById(Integer rewardPoints,Long userId) throws BusinessException;

    Users getUserById(Long userId) throws NotFoundException;
}
