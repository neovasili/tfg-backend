package com.juanmanuelruizfernandez.controller;

import com.juanmanuelruizfernandez.model.StoreControllerResponse;
import com.juanmanuelruizfernandez.model.Ticket;
import com.juanmanuelruizfernandez.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
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
        headers.add( "Content-Type", "application/json; charset=UTF-8" );
        this.storeControllerResponse = new StoreControllerResponse();
    }

    @RequestMapping( path = "/{storeID}", method = RequestMethod.GET, produces = "application/json" )
    public ResponseEntity< StoreControllerResponse > getStoreTicket( @PathVariable( value = "storeID" ) String storeID )
            throws IOException {

        List< Ticket > ticketList = storeService.getStoreTickets( storeID );

        this.storeControllerResponse.setTicketList( ticketList );

        return new ResponseEntity< StoreControllerResponse >( this.storeControllerResponse,
                this.headers,
                HttpStatus.OK );
    }
}
