package edu.neu.cs6510.util.sqs;

import edu.neu.cs6510.enums.EMessageType;

public class MessageWapper {

    EMessageType messageType;
    String id;
    Long timestamp;

    public MessageWapper(EMessageType messageType, String id) {
        this.messageType = messageType;
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }

    public EMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(EMessageType messageType) {
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
