package com.project.elevatehub.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ter", schema = "elevatehub")
public class Timesheet {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 50)
  @NotNull
  @Column(name = "project", nullable = false, length = 50)
  private String project;

  @Size(max = 50)
  @NotNull
  @Column(name = "task", nullable = false, length = 50)
  private String task;

  @Size(max = 50)
  @NotNull
  @Column(name = "terlocation", nullable = false, length = 50)
  private String terlocation;

  @Size(max = 50)
  @NotNull
  @Column(name = "terregion", nullable = false, length = 50)
  private String terregion;

  @Column(name = "workhours")
  private Integer workhours;

  @Size(max = 100)
  @NotNull
  @Column(name = "workItem", nullable = false, length = 100)
  private String workItem;

  @Size(max = 100)
  @NotNull
  @Column(name = "comment", nullable = false, length = 100)
  private String comment;
}
