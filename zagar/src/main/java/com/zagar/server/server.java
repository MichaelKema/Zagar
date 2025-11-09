package com.zagar.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.zagar.common.RoomManager;

public class server {
    public static final int DEFAULT_PORT = 12345;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port. Using default: " + DEFAULT_PORT);
            }
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            System.out.println("Listening for connections...");
            RoomManager.init();  // create default Lobby
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Incoming connection from " + clientSocket.getRemoteSocketAddress());
                new ServerThread(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}