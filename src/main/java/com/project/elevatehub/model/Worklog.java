package com.project.elevatehub.model;

import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "worklog", schema = "elevatehub")
public class Worklog {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @Column(name = "employee_id", nullable = false)
  private String employeeId;

  @NotNull
  @Column(name = "timespent", nullable = false)
  private Long timespent;

  @Size(max = 1000)
  @NotNull
  @Column(name = "comment", nullable = false, length = 1000)
  private String comment;

  @Column(name = "created_at")
  private Instant createdAt;
}
