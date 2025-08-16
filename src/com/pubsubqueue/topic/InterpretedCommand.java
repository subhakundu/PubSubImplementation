package com.pubsubqueue.topic;

public class InterpretedCommand {
    public MessageCommand messageCommand;
    public Topic topic;
    public Message message;

    public InterpretedCommand(MessageCommand messageCommand, Topic topic, Message message) {
        this.messageCommand = messageCommand;
        this.topic = topic;
        this.message = message;
    }
}
