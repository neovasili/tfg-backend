package com.juanmanuelruizfernandez.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
public class Ticket {

    private String ticketID = "empty";
    private String customerTicketID = "empty";
    @NotNull
    private String customerName = "Jhon Doe";
    @NotNull
    private String ticketAmount = "0,0";
    private String storeID = "1";

    public Ticket() {
    }

    public Ticket( String ticketID, String customerTicketID, String customerName, String ticketAmount, String storeID ) {
        this.ticketID = ticketID;
        this.customerTicketID = customerTicketID;
        this.customerName = customerName;
        this.ticketAmount = ticketAmount;
        this.storeID = storeID;
    }

    public Ticket( String ticketJSON ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Ticket ticket = objectMapper.readValue( ticketJSON, Ticket.class );

        this.ticketID = ticket.ticketID;
        this.customerTicketID = ticket.customerTicketID;
        this.customerName = ticket.customerName;
        this.ticketAmount = ticket.ticketAmount;
        this.storeID = ticket.storeID;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID( String ticketID ) {
        this.ticketID = ticketID;
    }

    public String getCustomerTicketID() {
        return customerTicketID;
    }

    public void setCustomerTicketID( String customerTicketID ) {
        this.customerTicketID = customerTicketID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName( String customerName ) {
        this.customerName = customerName;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount( String ticketAmount ) {
        this.ticketAmount = ticketAmount;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID( String storeID ) {
        this.storeID = storeID;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", customerTicketID='" + customerTicketID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", ticketAmount='" + ticketAmount + '\'' +
                ", storeID='" + storeID + '\'' +
                '}';
    }

    public String toJSON() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String ticketJSON = "";

        try {
            ticketJSON = objectMapper.writeValueAsString( this );
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }

        return ticketJSON;
    }
}
