package com.pubsubqueue.connectionpool;

import com.pubsubqueue.connectionpool.connection.Connection;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

public class PubSubThreadPool {
    public final int MAX_SIZE = 5;
    public final int STARTING_PORT = 9000;
    private ArrayBlockingQueue<Connection> connectionPool;

    public PubSubThreadPool() {
        connectionPool = new ArrayBlockingQueue<>(MAX_SIZE);
        for (int i=0; i<MAX_SIZE; i++) {

            try {
                connectionPool.offer(new Connection(STARTING_PORT + i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Lot of bad assumptions
     * 1. If port is occupied or not.
     * 2. Client has to send the port number. How to make it generic?
     * 3. If port size is more than permitted size, it does not send any message.
     */
    public Optional<Connection> getNewConnection() {
        try {
            int size = connectionPool.size();
            if (size == MAX_SIZE) {
               return Optional.empty();
            }
            return Optional.of(connectionPool.poll());
        } catch (Exception e) {
            System.out.println("Connection Failed for unknown reason.");
        }
        return Optional.empty();
    }
}
