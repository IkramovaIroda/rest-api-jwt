package com.restapijwt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

    private String errorMessage; //qanaqa error berdi
    private String requestedURI; //qaysi yo'lda /api/employee api/salary

}
