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
@Table(name = "untrackedhours", schema = "elevatehub")
public class Untrackedhours {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 50)
  @NotNull
  @Column(name = "source", nullable = false, length = 50)
  private String source;

  @Size(max = 10)
  @NotNull
  @Column(name = "employeeId", nullable = false, length = 10)
  private String employeeId;

  @NotNull
  @Column(name = "actionId", nullable = false)
  private String actionId;

  @NotNull
  @Column(name = "timespent", nullable = false)
  private Long timespent;

  @Column(name = "created_at")
  private Instant createdAt;
}
