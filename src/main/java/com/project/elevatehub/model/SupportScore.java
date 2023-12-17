package com.project.elevatehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "support_score", schema = "elevatehub")
public class SupportScore {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @Column(name = "employee_id", nullable = false)
  private String employeeId;

  @Size(max = 255)
  @Column(name = "email")
  private String email;

  @Size(max = 50)
  @NotNull
  @Column(name = "technologies", nullable = false, length = 50)
  private String technologies;

  @Size(max = 1000)
  @NotNull
  @Column(name = "comment", nullable = false, length = 1000)
  private String comment;

  @NotNull
  @Column(name = "rating", nullable = false)
  private Integer rating;

  @Column(name = "modified_at")
  private Instant modifiedAt;

  @CreationTimestamp
  @Column(name = "created_at")

  private Instant createdAt;


  @Column(name = "nominated_user")
  private String nominatedUser;

  @Column(name="badge_id")
  private Integer badgeId;


}
