package com.project.elevatehub.controller;

import com.project.elevatehub.constants.Status;
import com.project.elevatehub.model.ApiResponseModel;
import com.project.elevatehub.model.SupportScore;
import com.project.elevatehub.repository.SupportScoreRepository;
import com.project.elevatehub.service.JwtService;
import com.project.elevatehub.service.SupportScoreService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supportscore")
public class SupportScoreController {

  private static final Logger log = LoggerFactory.getLogger(SupportScoreController.class);


  @Autowired private SupportScoreService service;

  @Autowired
  SupportScoreRepository scoreRepository;

  @Autowired
  private JwtService jwtService;


  @PostMapping()
    public ResponseEntity<ApiResponseModel> save(HttpServletRequest request, @RequestBody SupportScore supportScore) {


    try {
      String authHeader = request.getHeader("Authorization");
      String token = null;
      String username = null;
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        username = jwtService.extractUsername(token);
      }
      supportScore.setNominatedUser(username);
      return ResponseEntity.ok().body(new ApiResponseModel(Status.SUCCESS, scoreRepository.save(supportScore)));
    } catch (Exception e) {
      log.error("User Id={}, Exception occurred in Get UserProfile by ID: ", e);
      ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

  }

  @GetMapping("/{emailId}")
  @Produces("application/json")
  public ResponseEntity<ApiResponseModel> getSupportScoreById(@PathVariable String emailId ) {
    try {
      log.debug("email Id: {} details requested", emailId);
      List<SupportScore> supportScores = service.findByEmail(emailId);
      return ResponseEntity.ok().body(new ApiResponseModel(Status.SUCCESS, supportScores));
    } catch (Exception e) {
      log.error("User Id={}, Exception occurred in Get UserProfile by ID: ", emailId, e);
      ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }



}
