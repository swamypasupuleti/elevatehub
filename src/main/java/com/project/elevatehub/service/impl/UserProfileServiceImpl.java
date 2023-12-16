package com.project.elevatehub.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elevatehub.constants.FileConstants;
import com.project.elevatehub.exception.BusinessException;
import com.project.elevatehub.model.entities.Users;
import com.project.elevatehub.repository.UsersRepository;
import com.project.elevatehub.service.UserProfileService;

import javassist.NotFoundException;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private static final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;
    
    @Override
    public void updateUserDetails(Long userId, Users newUser) {
        log.debug("Updating User ID: {} ", userId);
        updateName(userId, newUser.getName());
        updateEmail(userId, newUser.getEmail());
        updateCompanyName(userId, newUser.getCompanyName());
        updateAadharNumber(userId, newUser.getAadharNumber());
    }

    private void updateAadharNumber(Long userId, String aadharNumber) {
        if(aadharNumber != null && !aadharNumber.isEmpty())
            usersRepository.updateAadharNumber(userId, aadharNumber);
    }

    private void updateCompanyName(Long userId, String companyName) {
        if (companyName != null && !companyName.isEmpty())
            usersRepository.updateCompanyName(userId, companyName);
    }

    private void updateEmail(Long userId, String email) {
        if (email != null && !email.isEmpty())
            usersRepository.updateEmail(userId, email);
    }

    private void updateName(Long userId, String name) {
        if (name != null && !name.isEmpty())
            usersRepository.updateName(userId, name);
    }

    @Override
    public void updatePhoneNumber(Long userId, String phoneNumber) {
        if (phoneNumber != null && phoneNumber.isEmpty())
            usersRepository.updatePhoneNumber(userId, phoneNumber);
    }

 

    @Override
    public void enrichUserData(Users user) {
        user.setIsRegistered(true);
        user.setRewardPoints(0);
        if (user.getProfileImage() == null || user.getProfileImage().trim().isEmpty())
            user.setProfileImage(FileConstants.DEFAULT_PROFILE_IMAGE_S3_LOCATION);
    }

    @Override
    public Users findUserByPhoneNumber(String phoneNumber) throws BusinessException {
        return usersRepository.findUsersByPhoneNumber(phoneNumber);
    }

    @Override
    public Integer updateRewardPointsById(Integer rewardPoints, Long userId) throws BusinessException {
        return usersRepository.updateRewardPointsById(rewardPoints, userId);
    }

    @Override
    public Users getUserById(Long userId) throws NotFoundException {
        Optional<Users> storedUser = usersRepository.findById(userId);
        if(!storedUser.isPresent()) {
            log.error("User id:{} not found in DB",userId);
            throw new NotFoundException("User Not Found in DB");
        }
        return storedUser.get();
    }
}
