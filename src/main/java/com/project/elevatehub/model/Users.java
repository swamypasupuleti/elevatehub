package com.project.elevatehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "elevatehub")
public class Users {
  @Id
  @Size(max = 10)
  @Column(name = "employee_id", nullable = false, length = 10)
  private String employeeId;

  @NotNull
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 50)
  @NotNull
  @Column(name = "location", nullable = false, length = 50)
  private String location;

  @Size(max = 50)
  @NotNull
  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Size(max = 50)
  @NotNull
  @Column(name = "designation", nullable = false, length = 50)
  private String designation;

  @Size(max = 50)
  @NotNull
  @Column(name = "project_assigned", nullable = false, length = 50)
  private String projectAssigned;

  @Size(max = 10)
  @NotNull
  @Column(name = "line_manager", nullable = false, length = 10)
  private String lineManager;

  @Column(name = "created_at")
  private Instant createdAt;

  @Size(max = 255)
  @NotNull
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "modified_at")
  private Instant modifiedAt;

  @Size(max = 255)
  @Column(name = "name")
  private String name;

  @Size(max = 10)
  @Column(name = "phone_number", length = 10)
  private String phoneNumber;

  @Size(max = 255)
  @Column(name = "profile_image")
  private String profileImage;

  @Size(max = 30)
  @Column(name = "roles", length = 30)
  private String roles;

}
