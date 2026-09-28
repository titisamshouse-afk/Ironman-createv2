package com.ironman.main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public final class IronmanPlayerData {
    private static final Map<UUID, Integer> ENERGY = new HashMap<>();
    private static final int MAX = 1000;

    public static void initialize() {
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> ENERGY.remove(handler.player.getUUID()));
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (var player : server.getPlayerList().getPlayers()) {
                UUID id = player.getUUID();
                int value = ENERGY.getOrDefault(id, MAX);
                if (value < MAX && player.tickCount % 10 == 0) ENERGY.put(id, Math.min(MAX, value + 2));
            }
        });
    }

    public static int get(UUID id) { return ENERGY.getOrDefault(id, MAX); }
    public static boolean consume(UUID id, int amount) {
        int v = get(id);
        if (v < amount) return false;
        ENERGY.put(id, v - amount);
        return true;
    }
    public static int max() { return MAX; }
    private IronmanPlayerData() {}
}