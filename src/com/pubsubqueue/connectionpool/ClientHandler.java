package com.pubsubqueue.connectionpool;

import com.pubsubqueue.messagequeue.SubscribersQueue;
import com.pubsubqueue.topic.InterpretedCommand;
import com.pubsubqueue.topic.InterpretedCommandAdaptor;
import com.pubsubqueue.topic.MessageCommand;
import com.pubsubqueue.topic.TopicsQueue;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private SubscribersQueue subscribersQueue;
    private TopicsQueue topicsQueue;


    public ClientHandler(Socket socket, SubscribersQueue _subscribersQueue, TopicsQueue _topicsQueue) {
        clientSocket = socket;
        subscribersQueue = _subscribersQueue;
        topicsQueue = _topicsQueue;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
                InterpretedCommand interpretedCommand = InterpretedCommandAdaptor.createCommad(line);
                if (interpretedCommand.messageCommand.equals(MessageCommand.SUBSCRIBE)) {
                    System.out.println("Here");
                    subscribersQueue.pushNewSubscriber(interpretedCommand.topic.topicName, out);
                } else if (interpretedCommand.messageCommand.equals(MessageCommand.UNSUBSCRIBE)) {
                    subscribersQueue.removeASubscriber(interpretedCommand.topic.topicName, out);
                } else if (interpretedCommand.messageCommand.equals(MessageCommand.PUBLISH)) {
                    //enqueueMessage();
                    topicsQueue.pushMessage(interpretedCommand.topic.topicName, interpretedCommand.message.message);
                } else if (interpretedCommand.messageCommand.equals(MessageCommand.PULL)) {
                    List<String> messages = topicsQueue.getMessages(interpretedCommand.topic.topicName);
                    messages.forEach(message -> {
                        try {
                            out.println("Message received: " + message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    broadcast(interpretedCommand.topic.topicName);
                }
            }
        } catch (IOException e) {
            System.out.println("Can't establish channels for socket: " + clientSocket.getLocalPort());
        }
    }

    private void broadcast(String topicName) {
        List<String> messages = topicsQueue.getMessages(topicName);
        List<PrintWriter> subscribedBufferedWriters = subscribersQueue.getAllSubscribers(topicName);
        for (PrintWriter bs : subscribedBufferedWriters) {
            messages.forEach((message) -> {
                try {
                    System.out.println("Message: " + message + " written to: " + bs);
                    bs.println("Message: " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
