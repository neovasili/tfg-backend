package com.juanmanuelruizfernandez.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

public class SNSSubscription {

    @JsonProperty( "Type" )
    private String type;

    @JsonProperty( "MessageId" )
    private String messageId;

    @JsonProperty( "TopicArn" )
    private String topicArn;

    @JsonProperty( "Subject" )
    private String subject;

    @JsonProperty( "Message" )
    private String message;

    @JsonProperty( "Timestamp" )
    private String timestamp;

    @JsonProperty( "SubscribeURL" )
    private String subscribeURL;

    public SNSSubscription() {
    }

    public SNSSubscription( String jsonMessage ) {

        JSONObject json = new JSONObject( jsonMessage );

        if ( json.has( "Type" ) ) {
            this.type = json.get( "Type" ).toString();
        }

        if ( json.has( "MessageId" ) ) {
            this.messageId = json.get( "MessageId" ).toString();
        }

        if ( json.has( "TopicArn" ) ) {
            this.topicArn = json.get( "TopicArn" ).toString();
        }

        if ( json.has( "Subject" ) ) {
            this.subject = json.get( "Subject" ).toString();
        }

        if ( json.has( "Message" ) ) {
            this.message = json.get( "Message" ).toString();
        }

        if ( json.has( "Timestamp" ) ) {
            this.timestamp = json.get( "Timestamp" ).toString();
        }

        if ( json.has( "SubscribeURL" ) ) {
            this.subscribeURL = json.get( "SubscribeURL" ).toString();
        }
    }

    public SNSSubscription( String type,
                            String messageId,
                            String topicArn,
                            String subject,
                            String message,
                            String timestamp,
                            String subscribeURL ) {
        this.type = type;
        this.messageId = messageId;
        this.topicArn = topicArn;
        this.subject = subject;
        this.message = message;
        this.timestamp = timestamp;
        this.subscribeURL = subscribeURL;
    }

    public String getType() {
        return this.type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId( String messageId ) {
        this.messageId = messageId;
    }

    public String getTopicArn() {
        return this.topicArn;
    }

    public void setTopicArn( String topicArn ) {
        this.topicArn = topicArn;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject( String subject ) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp( String timestamp ) {
        this.timestamp = timestamp;
    }

    public String getSubscribeURL() {
        return this.subscribeURL;
    }

    public void setSubscribeURL( String subscribeURL ) {
        this.subscribeURL = subscribeURL;
    }

    @Override
    public String toString() {
        return "SNSSubscription{" +
                "type='" + this.type + '\'' +
                ", messageId='" + this.messageId + '\'' +
                ", topicArn='" + this.topicArn + '\'' +
                ", subject='" + this.subject + '\'' +
                ", message='" + this.message + '\'' +
                ", timestamp='" + this.timestamp + '\'' +
                ", subscribeURL='" + this.subscribeURL + '\'' +
                '}';
    }
}
