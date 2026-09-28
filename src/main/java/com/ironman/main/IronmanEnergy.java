package com.ironman.main;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class IronmanEnergy {
    private static final int MAX_ENERGY = 1000;
    private static final Map<UUID, Integer> ENERGY = new ConcurrentHashMap<>();
    public static void initialize() { }
    public static int get(UUID id) { return ENERGY.computeIfAbsent(id, k -> MAX_ENERGY); }
    public static int getMax() { return MAX_ENERGY; }
    public static boolean consume(UUID id, int amount) {
        int current = get(id); if (current < amount) return false; ENERGY.put(id, current - amount); return true;
    }
    public static void recharge(UUID id, int amount) { ENERGY.put(id, Math.min(MAX_ENERGY, get(id) + amount)); }
    private IronmanEnergy() {}
}
