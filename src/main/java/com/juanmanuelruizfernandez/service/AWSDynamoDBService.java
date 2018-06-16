package com.juanmanuelruizfernandez.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.juanmanuelruizfernandez.configuration.ConfigurationProperties;
import com.juanmanuelruizfernandez.model.Ticket;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class AWSDynamoDBService {

    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private DynamoDB dynamoDB = new DynamoDB( client );
    private ConfigurationProperties configurationProperties = new ConfigurationProperties();
    private String tableName = configurationProperties.getDynamoDBTableName();
    private Table table = dynamoDB.getTable( tableName );

    public Ticket save( Ticket ticket ) {

        String timestampID = String.valueOf( LocalDateTime.now().atZone( ZoneId.systemDefault() )
                .toInstant().toEpochMilli() );

        ticket.setTicketID( timestampID );
        ticket.setCustomerTicketID( timestampID.substring( 6 ) );

        Item item = new Item()
                .withPrimaryKey( "ticketID",
                        ticket.getTicketID() )
                .withString( "customerTicketID",
                        ticket.getCustomerTicketID() )
                .withString( "customerName",
                        ticket.getCustomerName() )
                .withString( "ticketAmount",
                        ticket.getTicketAmount() )
                .withString( "storeID",
                        ticket.getStoreID() );

        table.putItem( item );

        return ticket;
    }

    public Ticket getTicket( String ticketID ) throws IOException {

        Item item = table.getItem( "ticketID", ticketID );

        Ticket ticket = new Ticket( item.toJSON() );

        return ticket;
    }

    public List< Ticket > getTicketsFilteredByAttribute( String attributeName, String attributeValue ) throws IOException {

        List< Ticket > ticketList = new ArrayList< Ticket >();

        Map< String, Object > expressionAttributeValues = new HashMap< String, Object >();
        expressionAttributeValues.put( ":" + attributeValue, "1" );

        ItemCollection< ScanOutcome > items = table.scan( attributeName + " = :" + attributeValue,
                "ticketID, customerTicketID, customerName, ticketAmount, storeID",
                null,
                expressionAttributeValues );

        Iterator< Item > iterator = items.iterator();

        while ( iterator.hasNext() ) {
            ticketList.add( new Ticket( iterator.next().toJSON() ) );
        }

        return ticketList;
    }
}
