package com.juanmanuelruizfernandez.controller;

import com.juanmanuelruizfernandez.model.SNSSubscription;
import com.juanmanuelruizfernandez.model.Ticket;
import com.juanmanuelruizfernandez.model.TicketControllerResponse;
import com.juanmanuelruizfernandez.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Scanner;

@RestController
@RequestMapping( "/ticket" )
public class TicketController {

    @Autowired
    private SimpMessagingTemplate template;
    private TicketService ticketService;
    private HttpHeaders headers;
    private TicketControllerResponse ticketControllerResponse;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public TicketController( TicketService ticketService ) {
        this.ticketService = ticketService;
        this.headers = new HttpHeaders();
        headers.add( "Access-Control-Allow-Origin", "*" );
        this.ticketControllerResponse = new TicketControllerResponse();
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    @CrossOrigin( origins = "*" )
    @RequestMapping( method = RequestMethod.POST, produces = "application/json" )
    public ResponseEntity< TicketControllerResponse > createTicket( @Valid @RequestBody Ticket ticket ) {

        ticket = ticketService.create( ticket );

        this.ticketControllerResponse.setInvocator( "createTicket" );
        this.ticketControllerResponse.setTicket( ticket );

        return new ResponseEntity< TicketControllerResponse >( this.ticketControllerResponse,
                this.headers,
                HttpStatus.OK );
    }

    @CrossOrigin( origins = "*" )
    @RequestMapping( path = "/{ticketID}", method = RequestMethod.GET )
    public ResponseEntity< TicketControllerResponse > getTicket( @PathVariable( value = "ticketID" ) String ticketID )
            throws IOException {

        Ticket ticket = ticketService.getTicket( ticketID );

        this.ticketControllerResponse.setInvocator( "getTicket" );
        this.ticketControllerResponse.setTicket( ticket );

        return new ResponseEntity< TicketControllerResponse >( this.ticketControllerResponse,
                this.headers,
                HttpStatus.OK );
    }

    @RequestMapping( path = "/sns", method = RequestMethod.POST )
    public ResponseEntity< TicketControllerResponse > receiveTicket( HttpServletRequest request )
            throws Exception {

        Scanner scan = new Scanner( request.getInputStream() );
        StringBuilder builder = new StringBuilder();
        while ( scan.hasNextLine() ) {
            builder.append( scan.nextLine() );
        }
        SNSSubscription snsSubscription = new SNSSubscription( builder.toString() );

        this.ticketControllerResponse.setInvocator( "receiveTicket" );

        if ( snsSubscription.getSubscribeURL() != null ) {

            restTemplate.getForObject( snsSubscription.getSubscribeURL(), String.class );

            return new ResponseEntity< TicketControllerResponse >( this.ticketControllerResponse,
                    this.headers,
                    HttpStatus.OK );
        }

        template.convertAndSend( "/response/ticket", snsSubscription.getMessage() );

        this.ticketControllerResponse.setTicket( new Ticket( snsSubscription.getMessage() ) );

        return new ResponseEntity< TicketControllerResponse >( this.ticketControllerResponse,
                this.headers,
                HttpStatus.OK );
    }
}
