package com.example.projectBancoDados.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class Exception {

    private Integer status;
    private LocalDateTime time;
    private List<String> errors;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
