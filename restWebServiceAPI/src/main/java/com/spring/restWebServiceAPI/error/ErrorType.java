package com.spring.restWebServiceAPI.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorType {

    private String time;
    private String status;
    private String message;
}
