package com.juanmanuelruizfernandez.service;

import com.juanmanuelruizfernandez.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

	private AWSDynamoDBService awsDynamoDBService;

	@Autowired
	public StoreServiceImpl( AWSDynamoDBService awsDynamoDBService ) {
		this.awsDynamoDBService = awsDynamoDBService;
	}

	@Override
	public List< Ticket > getStoreTickets( String storeID ) throws IOException {

		List< Ticket > ticketList = this.awsDynamoDBService.getTicketsFilteredByAttribute( "storeID", storeID );

		return ticketList;
	}
}
