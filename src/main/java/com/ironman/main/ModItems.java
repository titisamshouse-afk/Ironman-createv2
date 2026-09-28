package com.ironman.main;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item ARC_REACTOR = register("arc_reactor", new Item(new Item.Properties().stacksTo(1)));
    public static final Item SUIT_CONTROLLER = register("suit_controller", new Item(new Item.Properties().stacksTo(1)));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(IronmanMod.MOD_ID, name), item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(ARC_REACTOR);
            entries.accept(SUIT_CONTROLLER);
        });
    }
    private ModItems() {}
}
