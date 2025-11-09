package com.zagar.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class RoomManager {
    private static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    private static final String LOBBY = "Lobby";

    public static void init() {
        rooms.put(LOBBY, new Room(LOBBY));
    }

    public static Room getLobby() {
        return rooms.get(LOBBY);
    }

    public static Room getRoom(String name) {
        return rooms.get(name);
    }

    public static synchronized Room createRoom(String name) {
        return rooms.computeIfAbsent(name, Room::new);
    }
}