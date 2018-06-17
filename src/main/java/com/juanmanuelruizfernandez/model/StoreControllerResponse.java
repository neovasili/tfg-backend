package com.juanmanuelruizfernandez.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoreControllerResponse extends ControllerResponse {

    private List< Ticket > ticketList = new ArrayList< Ticket >();

    public StoreControllerResponse() {
        this.setTimestamp( LocalDateTime.now() );
    }

    public List< Ticket > getTicketList() {
        return ticketList;
    }

    public void setTicketList( List< Ticket > ticketList ) {
        this.ticketList = ticketList;
    }

    public void addTicket( Ticket ticket ) {
        this.ticketList.add( ticket );
    }

    @Override
    public String toString() {
        return "StoreControllerResponse{" +
                "ticketList=" + ticketList +
                '}';
    }
}
