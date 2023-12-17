package com.project.elevatehub.service;

import com.project.elevatehub.model.SupportScore;

import java.util.List;

public interface SupportScoreService {

     List<SupportScore> findByEmail(String email);

}
