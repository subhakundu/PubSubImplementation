package com.pubsubqueue.topic;

import java.util.Arrays;

public class InterpretedCommandAdaptor {
    public static InterpretedCommand createCommad(String message) {
        String command = message.substring(0, message.indexOf(' '));
        MessageCommand messageCommand = getMessageCommandFromString(command);
        Topic topic = null;
        if(messageCommand.equals(MessageCommand.SUBSCRIBE) || messageCommand.equals(MessageCommand.UNSUBSCRIBE)
                || messageCommand.equals(MessageCommand.PULL) || messageCommand.equals(MessageCommand.BROADCAST)) {
            String topicMessage = message.substring(message.indexOf(' ') + 1);
            topic = new Topic(topicMessage);
            return new InterpretedCommand(messageCommand, topic, new Message("", topic));
        }
        String topicMessage = message.substring(message.indexOf(' ') + 1, message.indexOf('"'));
        topic = new Topic(topicMessage);
        String passedMessage = message.substring(message.indexOf('"'));
        return new InterpretedCommand(messageCommand, topic, new Message(passedMessage, topic));
    }

    public static MessageCommand getMessageCommandFromString(String suppliedCommand) {
//        return Arrays.stream(MessageCommand.values()).
//                filter(command -> command.equals(suppliedCommand)).
//                findFirst().orElse(null);
        for (MessageCommand value : MessageCommand.values()) {
            if (value.name().equals(suppliedCommand)) {
                return value;
            }
        }
        return null;
    }
}
