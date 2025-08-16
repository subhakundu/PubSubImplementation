package com.pubsubqueue.topic;

import java.util.Arrays;

public enum MessageCommand {
    // To publish a new message to the que for a topic. If topic does not exist, it gets created.
    PUBLISH,
    // To pull all pending messages for a topic in the queue.
    PULL,
    // Subscribe to a topic
    SUBSCRIBE,
    // Unsubscribe to a topic
    UNSUBSCRIBE,
    // Broadcast to all clients subscribed to a topic
    BROADCAST;

}
