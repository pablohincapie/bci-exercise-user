package com.bci.exercise.user.exceptionTest;

import com.bci.exercise.user.config.ErrorResponse;
import com.bci.exercise.user.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {
    @Test
    public void testHandleInternalServerError() {

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception testException = new Exception("Se presento un error");

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleInternalServerError(testException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals("Ocurrió un error interno en el servidor. Por favor, inténtelo de nuevo más tarde.", errorResponse.getDetail());

    }
}
