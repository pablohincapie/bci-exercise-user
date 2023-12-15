package com.bci.exercise.user.config;

import org.springframework.http.HttpStatus;
import java.sql.Timestamp;

public class ErrorResponse {

    private Timestamp timestamp;
    private HttpStatus codigo;
    private String detail;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = HttpStatus.valueOf(codigo);
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ErrorResponse(Timestamp timestamp, HttpStatus codigo, String detail) {
        this.timestamp = timestamp;
        this.codigo = codigo;
        this.detail = detail;
    }

}
