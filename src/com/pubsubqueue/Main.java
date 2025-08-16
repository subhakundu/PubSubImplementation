package com.pubsubqueue;

import com.pubsubqueue.connectionpool.ClientHandler;
import com.pubsubqueue.messagequeue.SubscribersQueue;
import com.pubsubqueue.topic.TopicsQueue;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, List<Socket>> consumerTopicConnectionPull = new HashMap<>();
        SubscribersQueue subscribersQueue = new SubscribersQueue();
	    // write your code here
        System.out.println("Creating the PubSub queue");
        System.out.println("Creating the connection poll..");

        ExecutorService pool = Executors.newFixedThreadPool(10);
        TopicsQueue topicsQueue = new TopicsQueue();
        ServerSocket serverSocket = new ServerSocket(9000);

        while(true) {
            Socket clientSocket = serverSocket.accept();
            pool.submit(new ClientHandler(clientSocket, subscribersQueue, topicsQueue));
        }
    }
}
