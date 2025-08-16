package com.pubsubqueue.connectionpool.connection;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

public class Connection {
    private int port;
    private ServerSocket socket;
    private BufferedReader instream;
    private boolean isOpen;

    public Connection(int _port) throws IOException {
        port = _port;
        socket = new ServerSocket(port);
        isOpen = true;
    }

    public ServerSocket getSocket() {
       return socket;
    }
}
