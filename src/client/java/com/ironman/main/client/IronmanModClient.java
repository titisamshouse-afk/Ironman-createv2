package com.ironman.main.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class IronmanModClient implements ClientModInitializer {
    private static final KeyMapping SUIT_TOGGLE = KeyBindingHelper.registerKeyBinding(
        new KeyMapping("key.ironman.toggle_suit", GLFW.GLFW_KEY_G, "category.ironman"));
    private static final KeyMapping FLIGHT = KeyBindingHelper.registerKeyBinding(
        new KeyMapping("key.ironman.flight", GLFW.GLFW_KEY_F, "category.ironman"));
    private static boolean suitActive;
    private static boolean flightActive;

    @Override public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(IronmanModClient::tick);
    }

    private static void tick(Minecraft client) {
        while (SUIT_TOGGLE.consumeClick()) { suitActive = !suitActive; if (!suitActive) flightActive = false; }
        while (FLIGHT.consumeClick()) { if (suitActive) flightActive = !flightActive; }
        if (client.player == null || !suitActive || !flightActive) return;
        double vertical = 0;
        if (client.options.keyJump.isDown()) vertical += 0.55;
        if (client.options.keyShift.isDown()) vertical -= 0.55;
        var look = client.player.getLookAngle();
        double forward = client.options.keyUp.isDown() ? 0.85 : 0.0;
        client.player.setDeltaMovement(look.x * forward, vertical, look.z * forward);
        client.player.hurtMarked = true;
    }
    public static boolean isSuitActive() { return suitActive; }
    public static boolean isFlightActive() { return flightActive; }
}
