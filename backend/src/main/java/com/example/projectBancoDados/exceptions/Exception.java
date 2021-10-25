package com.example.projectBancoDados.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class Exception {

    private Integer status;
    private LocalDateTime timestamp;
    private List<String> errors;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(LocalDateTime time) {
        this.timestamp = time;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
