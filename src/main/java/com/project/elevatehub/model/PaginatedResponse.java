package com.project.elevatehub.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse {

	private Object entries;
	private Pagination pagination;
	
	public PaginatedResponse(Object entries, Pagination pagination) {
		this.entries = entries;
		this.pagination = pagination;
	}
}
