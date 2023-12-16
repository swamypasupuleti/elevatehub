package com.project.elevatehub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pagination {
	
	private Integer count;
	private Long totalItems;
	private Integer skipCount;
	private Integer maxItems;
	
	public Pagination(Integer skipCount, Integer maxItems) {
	  this.skipCount = skipCount;
      this.maxItems = maxItems;
    }
}
