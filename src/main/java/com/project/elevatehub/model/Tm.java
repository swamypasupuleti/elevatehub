package com.project.elevatehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tms", schema = "elevatehub")
public class Tm {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ticketId", nullable = false)
  private Long id;

  @Size(max = 50)
  @NotNull
  @Column(name = "ticketKey", nullable = false, length = 50)
  private String ticketKey;

  @Column(name = "parentId")
  private Long parentId;

  @Size(max = 50)
  @NotNull
  @Column(name = "priority", nullable = false, length = 50)
  private String priority;

  @Size(max = 50)
  @NotNull
  @Column(name = "status", nullable = false, length = 50)
  private String status;

  @NotNull
  @Column(name = "created", nullable = false)
  private Instant created;

  @Size(max = 50)
  @Column(name = "assignee", length = 50)
  private String assignee;

  @Size(max = 50)
  @Column(name = "summary", length = 50)
  private String summary;

  @Column(name = "originalEstimate")
  private Long originalEstimate;
}
