package com.ironman.main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;

public final class IronmanPlayerData {
    private static final Map<UUID, Integer> ENERGY = new HashMap<>();
    private static final int MAX = 1000;

    public static void initialize() {
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) ->
                ENERGY.remove(handler.player.getUUID()));

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                UUID id = player.getUUID();
                boolean suited = hasFullSuit(player);

                if (!suited) {
                    if (player.getAbilities().mayfly) {
                        player.getAbilities().mayfly = false;
                        player.onUpdateAbilities();
                    }
                    ENERGY.putIfAbsent(id, MAX);
                    continue;
                }

                ENERGY.putIfAbsent(id, MAX);
                int energy = get(id);

                // A complete suit grants real server-side flight.
                if (energy > 0 && !player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = true;
                    player.getAbilities().flying = false;
                    player.getAbilities().setFlyingSpeed(0.08F);
                    player.onUpdateAbilities();
                }

                // Flight consumes energy and produces thruster effects.
                if (player.getAbilities().flying && player.tickCount % 5 == 0) {
                    if (!consume(id, 2)) {
                        player.getAbilities().flying = false;
                        player.getAbilities().mayfly = false;
                        player.onUpdateAbilities();
                    } else {
                        ((net.minecraft.server.level.ServerLevel) player.level()).sendParticles(ParticleTypes.FLAME,
                                player.getX(), player.getY() + 0.15, player.getZ(),
                                4, 0.22, 0.05, 0.22, 0.01);
                        if (player.tickCount % 20 == 0) {
                            player.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 0.18F, 1.8F);
                        }
                    }
                } else if (energy < MAX && player.tickCount % 10 == 0) {
                    recharge(id, 2);
                }

                if (energy <= 0 && player.getAbilities().flying) {
                    player.getAbilities().flying = false;
                }
            }
        });
    }

    public static boolean hasFullSuit(ServerPlayer player) {
        return player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModArmorItems.HELMET)
                && player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModArmorItems.CHESTPLATE)
                && player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModArmorItems.LEGGINGS)
                && player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModArmorItems.BOOTS);
    }

    public static int get(UUID id) {
        return ENERGY.getOrDefault(id, MAX);
    }

    public static boolean consume(UUID id, int amount) {
        int value = get(id);
        if (value < amount) return false;
        ENERGY.put(id, value - amount);
        return true;
    }

    public static void recharge(UUID id, int amount) {
        ENERGY.put(id, Math.min(MAX, get(id) + amount));
    }

    public static int max() {
        return MAX;
    }

    private IronmanPlayerData() {}
}
