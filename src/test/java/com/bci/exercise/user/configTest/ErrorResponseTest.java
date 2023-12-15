package com.bci.exercise.user.configTest;

import com.bci.exercise.user.config.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {

        Timestamp expectedTimestamp = new Timestamp(System.currentTimeMillis());
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;
        String expectedDetail = "Se presento un error en los campos enviados";

        ErrorResponse errorResponse = new ErrorResponse(expectedTimestamp, expectedHttpStatus, expectedDetail);

        assertEquals(expectedTimestamp, errorResponse.getTimestamp());
        assertEquals(expectedHttpStatus, errorResponse.getCodigo());
        assertEquals(expectedDetail, errorResponse.getDetail());
    }

    @Test
    public void testSetCodigo() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.OK, "Error de servidor");

        errorResponse.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getCodigo());
    }

    @Test
    public void testSetDetail() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.OK, "Se presento un error");

        errorResponse.setDetail("Se presento un error");
        assertEquals("Se presento un error", errorResponse.getDetail());
    }
}
