package com.juanmanuelruizfernandez.service;

import com.juanmanuelruizfernandez.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TicketServiceImpl implements TicketService {

	private AWSDynamoDBService awsDynamoDBService;

	@Autowired
	public TicketServiceImpl( AWSDynamoDBService awsDynamoDBService ) {
		this.awsDynamoDBService = awsDynamoDBService;
	}

	@Override
	public Ticket create(Ticket ticket ) {

		ticket = this.awsDynamoDBService.save( ticket );

		return ticket;
	}

	@Override
	public Ticket getTicket( String ticketID ) throws IOException {

		return this.awsDynamoDBService.getTicket( ticketID );
	}
}
