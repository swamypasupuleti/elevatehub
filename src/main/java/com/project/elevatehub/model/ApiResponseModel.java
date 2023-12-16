package com.project.elevatehub.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseModel {
    private String status;
    private Object data;
    private String message;
    private Date responseTimestamp;

    public ApiResponseModel(String status, Object data) {
        this.status = status;
        this.data = data;
        this.responseTimestamp = new Timestamp(new Date().getTime());
        this.message = null;
    }

    public ApiResponseModel(String status, String message) {
        this.status = status;
        this.data = null;
        this.responseTimestamp = new Timestamp(new Date().getTime());
        this.message = message;
    }

}
