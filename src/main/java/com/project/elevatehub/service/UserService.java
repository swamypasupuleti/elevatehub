package com.project.elevatehub.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

public interface UserService {

     List<Object> getVelocityByFilterType(String type);

}
