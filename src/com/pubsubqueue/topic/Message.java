package com.pubsubqueue.topic;

public class Message {
    public String message;
    public Topic topic;

    public Message(String message, Topic topic) {
        this.message = message;
        this.topic = topic;
    }
}
