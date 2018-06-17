package com.juanmanuelruizfernandez.model;

import java.time.LocalDateTime;

public class
TicketControllerResponse extends ControllerResponse {

    private Ticket ticket;

    public TicketControllerResponse() {
        this.setTimestamp( LocalDateTime.now() );
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket( Ticket ticket ) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "TicketControllerResponse{" +
                "ticket=" + ticket +
                '}';
    }
}
