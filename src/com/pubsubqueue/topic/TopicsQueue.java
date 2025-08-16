package com.pubsubqueue.topic;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class TopicsQueue {
    // Must be made configurable later.
    public static final int MAX_TOPIC_QUEUE_SIZE = 10;
    // FOr now, starting with String messages
    Map<String, List<String>> mapOfTopics = new HashMap<>();

    public void pushMessage(String topic, String message) {
        List<String> topicQueue = mapOfTopics.getOrDefault(topic, new ArrayList<>(MAX_TOPIC_QUEUE_SIZE));
        topicQueue.add(message);
        mapOfTopics.put(topic, topicQueue);
    }
    // Need to create pending message queue, to stop delivering old messages
    public List<String> getMessages(String topic) {
        return mapOfTopics.get(topic);
    }
}
