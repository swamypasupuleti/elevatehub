package com.project.elevatehub.service;


import java.util.Map;

public interface UserService {

     Map<String, Object> getVelocityByFilterType(String type, String employeeId, String projectCode);

}
