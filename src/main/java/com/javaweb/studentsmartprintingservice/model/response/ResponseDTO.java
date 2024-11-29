package com.javaweb.studentsmartprintingservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDTO {
    private Long entityId;
    private Object data;
    private String message;
    private List<String> detail;
}
