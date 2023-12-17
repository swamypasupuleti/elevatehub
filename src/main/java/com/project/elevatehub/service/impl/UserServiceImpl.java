package com.project.elevatehub.service.impl;

import static com.project.elevatehub.constants.FileConstants.*;

import com.project.elevatehub.repository.TerRepository;
import com.project.elevatehub.repository.UntrackedHoursRepository;
import com.project.elevatehub.repository.WorkLogRepository;
import com.project.elevatehub.service.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WorkLogRepository workLogRepository;


    @Autowired
    TerRepository terRepository;

    @Autowired
    UntrackedHoursRepository untrackedHoursRepository;

    public Map<String, Object> getVelocityByFilterType(String type, String employeeId, String projectCode){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer interval = type.equals(LAST_15_DAYS) ? 15
                :type.equals(LAST_30_DAYS) ? 30 :60;
        List<Object> worklog =  workLogRepository.getWorkLogByInterval(employeeId, projectCode,interval);
        List<Object> timesheet =  terRepository.getWorkLogByInterval(employeeId, projectCode,interval);
        List<Object> untrackedHours =  untrackedHoursRepository.getUntrackedHoursByInterval(employeeId,interval);

        map.put("worklog",worklog);
        map.put("timesheet",timesheet);
        map.put("untrackedHours",untrackedHours);
        return map;
    }
}
