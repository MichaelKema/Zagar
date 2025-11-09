package com.zagar.common;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import com.zagar.server.ServerThread;

public class Room {
    private final String name;
    private final Set<ServerThread> clients = Collections.synchronizedSet(new HashSet<>());

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addClient(ServerThread client) {
        clients.add(client);
        broadcast(client.getClientName() + " joined the room.");
    }

    public void removeClient(ServerThread client) {
        clients.remove(client);
        broadcast(client.getClientName() + " left the room.");
    }

    public void broadcast(String message) {
        synchronized (clients) {
            for (ServerThread c : clients) {
                c.sendMessage(message);
            }
        }
    }



}