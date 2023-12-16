package com.project.elevatehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "worklog", schema = "elevatehub")
public class Badges {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 10)
  @Column(name = "name")
  private String name;
}
