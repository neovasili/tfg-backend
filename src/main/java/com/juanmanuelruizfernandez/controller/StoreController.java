package com.juanmanuelruizfernandez.controller;

import com.juanmanuelruizfernandez.model.StoreControllerResponse;
import com.juanmanuelruizfernandez.model.Ticket;
import com.juanmanuelruizfernandez.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping( "/store" )
public class StoreController {

	private StoreService storeService;
	private HttpHeaders headers;
	private StoreControllerResponse storeControllerResponse;

	@Autowired
	public StoreController( StoreService storeService ) {
		this.storeService = storeService;
		this.headers = new HttpHeaders();
		headers.add( "Access-Control-Allow-Origin", "*" );
		this.storeControllerResponse = new StoreControllerResponse();
	}

    @CrossOrigin( origins = "*" )
	@RequestMapping( path = "/{storeID}", method = RequestMethod.GET )
	public ResponseEntity< StoreControllerResponse > getStoreTicket( @PathVariable( value = "storeID" ) String storeID )
			throws IOException {

		List< Ticket > ticketList = storeService.getStoreTickets( storeID );

		this.storeControllerResponse.setTicketList( ticketList );

		HttpHeaders headers = new HttpHeaders();
		headers.add( "Access-Control-Allow-Origin", "*" );

		return new ResponseEntity< StoreControllerResponse >( this.storeControllerResponse,
				this.headers,
				HttpStatus.OK );
	}
}
