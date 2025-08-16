# PubSubImplementation
This is an implementation based on Kafka from Scratch using Java.

## Operations Supported
**1. PUBLISH:** Publish a message to a particular topic. If topic is not present, it will be created.
**2. SUBSCRIBE:** As a subscriber, subscribe to a topic.
**3. UNSUBSCRIBE:** Unsubscribe from a topic. Not verified yet.
**4. PULL:** A subscriber can pull all messages for a topic.
**5. BROADCAST:** Broadcast all messages for a topic to all the subscribers.

## Next Steps
1. Need to refactor the code. It is all scrappy now.
2. Need to make executable configurable like max number of clients, size of queue.
3. Need to try out how Kafka writes messages from socket to socket, an optimization.
4. [Good to have] Currently the format of the messages is not versatile. Need to make it better.
