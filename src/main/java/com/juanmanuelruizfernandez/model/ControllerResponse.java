package com.juanmanuelruizfernandez.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class ControllerResponse {

    private String invocator = "none";
    @NotNull
    private String type = "notification";
    private LocalDateTime timestamp;
    @NotNull
    private String status = "OK";

    public ControllerResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ControllerResponse( String invocator ) {
        this.timestamp = LocalDateTime.now();
        this.invocator = invocator;
    }

    public ControllerResponse( String invocator, String status ) {
        this.timestamp = LocalDateTime.now();
        this.invocator = invocator;
        this.status = status;
    }

    public String getInvocator() {
        return invocator;
    }

    public void setInvocator( String invocator ) {
        this.invocator = invocator;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp( LocalDateTime timestamp ) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ControllerResponse{" +
                "invocator='" + invocator + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}
