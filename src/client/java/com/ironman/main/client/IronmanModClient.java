package com.ironman.main.client;

import com.ironman.main.IronmanPlayerData;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class IronmanModClient implements ClientModInitializer {
    private static final KeyMapping SUIT_INFO = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.ironman.suit_info", InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_G, "category.ironman"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (SUIT_INFO.consumeClick()) {
                // The suit is activated automatically by wearing all four pieces.
            }
        });

        HudRenderCallback.EVENT.register(IronmanModClient::renderHud);
    }

    private static void renderHud(GuiGraphics graphics, float tickDelta) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        boolean fullSuit =
                client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(com.ironman.main.ModArmorItems.HELMET)
                && client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(com.ironman.main.ModArmorItems.CHESTPLATE)
                && client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(com.ironman.main.ModArmorItems.LEGGINGS)
                && client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(com.ironman.main.ModArmorItems.BOOTS);

        if (!fullSuit) return;

        // Client HUD reads the synced player abilities/state visually.
        int x = 10;
        int y = 10;
        graphics.drawString(client.font, "IRON MAN", x, y, 0xFFFFFF, true);
        graphics.drawString(client.font,
                client.player.getAbilities().flying ? "FLIGHT: ON" : "FLIGHT: READY",
                x, y + 12, 0xFFFFFF, true);
        graphics.drawString(client.font,
                "Wear full suit • Space to fly",
                x, y + 24, 0xFFFFFF, true);
    }
}
