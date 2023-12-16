package com.project.elevatehub.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.elevatehub.exception.DataValidationException;
import com.project.elevatehub.model.entities.Users;
import com.project.elevatehub.repository.UsersRepository;

import javassist.NotFoundException;

@Component
public class Validator {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    FileUtils fileUtils;

    /**
     * Ensures that user data like Registered status, phoneNumber, Reward Points are not updated as part of this API
     * @param user
     */
    public void userDetailsUpdate(Users user) {

        String status = null;

        if(user.getIsRegistered() != null)
            status = "Update Error: Not allowed to update user status!";

        if(user.getPhoneNumber() != null)
            status = "Update Error: Not allowed to update user Phone Number through this API.";

        if(user.getRewardPoints() != null)
            status = "Update Error: Not allowed to update Reward Points through this API";

        if(status != null)
            throw new DataValidationException(status);
    }

    /**
     * Checks if user with provided phone number already exists in DB
     * @param phoneNumber
     */
    public void validatePhoneNumber(String phoneNumber) {
        boolean doesPhNoexists = usersRepository.existsByPhoneNumber(phoneNumber);

        if(doesPhNoexists)
            throw new DataValidationException("User with this Phone Number already exists");
    }


    public void validateImage(MultipartFile file) {
        if(file == null)
            throw new DataValidationException("Cannot accept empty image file");

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        //check if the file name contains any invalid characters
        if(originalFileName.contains(".."))
            throw new DataValidationException("Try again! FileName contains invalid path sequence " + originalFileName);

        fileUtils.verifyFileTypeMatch(originalFileName);

    }

    public void validateUserId(Long userId) throws NotFoundException {
        boolean doesIdExists = usersRepository.existsById(userId);

        if(!doesIdExists)
            throw new NotFoundException("Error! User id "+ userId +" not found in the database");

    }

}
