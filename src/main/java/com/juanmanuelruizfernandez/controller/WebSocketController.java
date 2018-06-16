package com.juanmanuelruizfernandez.controller;

import com.juanmanuelruizfernandez.model.ControllerResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping( "/ack" )
    @SendTo( "/response/ticket" )
    public ControllerResponse receiveTicket( String snsMessage ) {

        ControllerResponse controllerResponse = new ControllerResponse();

        controllerResponse.setInvocator( "receiveTicketWebSocket" );

        return controllerResponse;
    }

}
