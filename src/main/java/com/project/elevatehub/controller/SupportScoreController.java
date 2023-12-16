package com.project.elevatehub.controller;

import com.project.elevatehub.model.entities.SupportScore;
import com.project.elevatehub.service.SupportScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supportscore")
public class SupportScoreController {

  @Autowired private SupportScoreService service;
  @GetMapping("/{email}")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public SupportScore getSupportScoreById(@PathVariable String emailId) {
    return service.findByEmail(emailId);
  }

  @PostMapping("/{email}")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public SupportScore updateFeedback(@PathVariable String emailId) {
    return service.findByEmail(emailId);
  }
}
