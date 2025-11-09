package com.zagar.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.zagar.common.Room;
import com.zagar.common.RoomManager;

public class ServerThread extends Thread {
    private final Socket socket;
    private String clientName;
    private Room currentRoom;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.clientName = null;
    }

    @Override
    public void run() {
        try {
            out.println("Welcome! Please set your name with /name <your_name>");
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("/name ")) {
                    handleName(line.substring(6).trim());
                } else if (clientName == null) {
                    out.println("Set your name first with /name <your_name>");
                } else if (line.startsWith("/create ")) {
                    handleCreate(line.substring(8).trim());
                } else if (line.startsWith("/join ")) {
                    handleJoin(line.substring(6).trim());
                } else if (line.equals("/leave")) {
                    handleLeave();
                } else if (line.startsWith("/quit")) {
                    break;
                } else {
                    handleMessage(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Connection error with client " + clientName + ": " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void handleName(String name) {
        this.clientName = name;
        out.println("Name set to " + name);
        // join lobby
        currentRoom = RoomManager.getLobby();
        currentRoom.addClient(this);
    }

    private void handleCreate(String roomName) {
        Room newRoom = RoomManager.createRoom(roomName);
        changeRoom(newRoom);
        out.println("Room '" + roomName + "' created and joined.");
    }

    private void handleJoin(String roomName) {
        Room room = RoomManager.getRoom(roomName);
        if (room == null) {
            out.println("Room '" + roomName + "' does not exist.");
        } else {
            changeRoom(room);
            out.println("Joined room '" + roomName + "'.");
        }
    }

    private void handleLeave() {
        changeRoom(RoomManager.getLobby());
        out.println("Returned to Lobby.");
    }

    private void handleMessage(String msg) {
        if (currentRoom != null) {
            currentRoom.broadcast(clientName + ": " + msg);
        }
    }

    private void changeRoom(Room newRoom) {
        if (currentRoom != null) {
            currentRoom.removeClient(this);
        }
        currentRoom = newRoom;
        currentRoom.addClient(this);
    }

    private void disconnect() {
        try {
            if (currentRoom != null && clientName != null) {
                currentRoom.removeClient(this);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}