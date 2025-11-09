package com.zagar.client;

// Java program demonstrates the 
// working of client-side programming
// using ServerSocket class
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class client {
    private static volatile boolean connected = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userName = null;
        Socket socket = null;
        PrintWriter out = null;

        System.out.println("Client started. Waiting for input.");
        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("/name ")) {
                userName = input.substring(6).trim();
                System.out.println("Name set to " + userName);
            } else if (input.startsWith("/connect ")) {
                if (userName == null) {
                    System.out.println("Set your name first with /name <your_name>");
                    continue;
                }
                String[] parts = input.split("\\s+");
                if (parts.length < 3) {
                    System.out.println("Usage: /connect <host> <port>");
                    continue;
                }
                String host = parts[1];
                int port;
                try {
                    port = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port number.");
                    continue;
                }
                try {
                    socket = new Socket(host, port);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    connected = true;
                    System.out.println("Connected to server");
                    // send name to server
                    out.println("/name " + userName);
                    // start listener thread
                    Thread listener = new Thread(() -> {
                        try {
                            String serverMsg;
                            while ((serverMsg = in.readLine()) != null) {
                                System.out.println(serverMsg);
                            }
                        } catch (IOException e) {
                            if (connected) System.out.println("Disconnected from server");
                        } finally {
                            connected = false;
                        }
                    });
                    listener.start();
                } catch (IOException e) {
                    System.out.println("Unable to connect to server: " + e.getMessage());
                }
            } else if (input.startsWith("/quit")) {
                System.out.println("Exiting client.");
                break;
            } else {
                if (connected && out != null) {
                    out.println(input);
                } else {
                    System.out.println("Not connected. Use /connect <host> <port> to connect.");
                }
            }
        }
        scanner.close();
    }
}