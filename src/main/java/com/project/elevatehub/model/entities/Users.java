package com.project.elevatehub.model.entities;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users", schema = "elevatehub")
@Getter
@Setter
@NoArgsConstructor
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String employeeId;

  @NotBlank(message = "Phone Number is mandatory")
  @Size(min = 10, max = 10, message = "Phone Number is expected to be 10 characters long")
  private String phoneNumber;

  @NotBlank(message = "Name is mandatory")
  private String name;

  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email ID is not in expected format")
  private String email;

  private String roles;

  private String password;

  private String profileImage;

  private String location;

  private String designation;

  private String projectAssigned;

  private String lineManager;

  @CreationTimestamp private Timestamp createdAt;

  @UpdateTimestamp private Timestamp modifiedAt;

  Users(
      String employeeId,
      String phoneNumber,
      String name,
      String email,
      String location,
      String designation,
      String projectAssigned,
      String lineManager,
      String profileImage) {
    this.employeeId = employeeId;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.email = email;
    this.location = location;
    this.designation = designation;
    this.projectAssigned = projectAssigned;
    this.lineManager = lineManager;
    this.profileImage = profileImage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        employeeId,
        phoneNumber,
        name,
        email,
        location,
        designation,
        projectAssigned,
        lineManager,
        profileImage,
        createdAt,
        modifiedAt);
  }

  @Override
  public String toString() {
    return "UserId: "
        + id
        + ", "
        + "Employee Id: "
        + employeeId
        + ", "
        + "Name: "
        + name
        + ", "
        + "Phone Number: "
        + phoneNumber
        + ", "
        + "Email: "
        + email
        + ", "
        + "Company Name: "
        + location
        + ", "
        + "Reward Points: "
        + designation
        + ", "
        + "Registered: "
        + projectAssigned
        + ", ";
  }
}
