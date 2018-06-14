package com.juanmanuelruizfernandez.service;

import com.juanmanuelruizfernandez.model.Ticket;

import java.io.IOException;

public interface TicketService {

	Ticket create(Ticket ticket );

	Ticket getTicket( String ticketID ) throws IOException;
}
