package com.project.elevatehub.service.impl;

import com.project.elevatehub.model.SupportScore;
import com.project.elevatehub.repository.SupportScoreRepository;
import com.project.elevatehub.service.SupportScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportScoreServiceImpl implements SupportScoreService {


    @Autowired
    SupportScoreRepository scoreRepository;

    @Override
    public List<SupportScore> findByEmail(String email) {

        return scoreRepository.findByEmail(email);
    }
}
