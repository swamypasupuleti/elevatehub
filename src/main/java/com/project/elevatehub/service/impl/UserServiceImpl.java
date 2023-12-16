package com.project.elevatehub.service.impl;

import com.project.elevatehub.repository.UsersRepository;
import com.project.elevatehub.repository.WorkLogRepository;
import com.project.elevatehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.elevatehub.constants.FileConstants.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WorkLogRepository workLogRepository;

    public  List<Object> getVelocityByFilterType(String type){
     //JSONObject velocityObj = new JSONObject();

        List<Object> list = new ArrayList<>();
        switch(type){
            case CURRENT_MONTH: list =  workLogRepository.getCurrentMonthWorkLog(type);
                break;
//            case PREVIOUS_MONTH: list =  workLogRepository.getPreviousMonthWorkLog(new Date());
//                break;
//            case CURRENT_YEAR: list =  workLogRepository.getPreviousYearWorkLog(new Date());
//                break;
        }
        return list;
    }
}
