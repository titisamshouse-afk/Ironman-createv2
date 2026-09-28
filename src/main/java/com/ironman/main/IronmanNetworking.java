package com.ironman.main;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public final class IronmanNetworking {
    public static void initialize() {
        // Network channel reserved for future client/server suit controls.
        // Core suit state remains server-owned.
    }
    private IronmanNetworking() {}
}