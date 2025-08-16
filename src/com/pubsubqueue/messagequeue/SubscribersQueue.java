package com.pubsubqueue.messagequeue;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SubscribersQueue {
    private final ConcurrentHashMap<String, List<PrintWriter>> mapOfSubscribersForTopic =
            new ConcurrentHashMap<>();

    public void pushNewSubscriber(String topic, PrintWriter outputStream) {
        List<PrintWriter> topicSubscribers = mapOfSubscribersForTopic.getOrDefault(topic, new ArrayList<>());
        topicSubscribers.add(outputStream);
        mapOfSubscribersForTopic.put(topic, topicSubscribers);
    }

    public void removeASubscriber(String topic, PrintWriter outputStream) {
        List<PrintWriter> topicSubscribers = mapOfSubscribersForTopic.getOrDefault(topic, new ArrayList<>());
        topicSubscribers.remove(outputStream);
        mapOfSubscribersForTopic.put(topic, topicSubscribers);
    }

    public List<PrintWriter> getAllSubscribers(String topic) {
        return mapOfSubscribersForTopic.getOrDefault(topic, new ArrayList<>());
    }
}
