package com.project.elevatehub.controller;


import com.project.elevatehub.dto.AuthRequest;
import com.project.elevatehub.exception.DataValidationException;
import com.project.elevatehub.model.Users;
import com.project.elevatehub.repository.UsersRepository;
import com.project.elevatehub.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserProfileResource {

    private static final Logger log = LoggerFactory.getLogger(UserProfileResource.class);
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/authenticate")
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Map<String, String> map = new HashMap<String, String>();
        Users userInfo = usersRepository.findByUserNameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        System.out.println(userInfo.getEmployeeId());
        if (userInfo != null) {
            String token = jwtService.generateToken(authRequest.getUsername());
            map.put("token", "Bearer " + token);
            return map;
        } else {
            throw new DataValidationException("invalid user request !");
        }
    }
}
