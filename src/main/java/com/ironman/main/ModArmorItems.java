package com.ironman.main;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public final class ModArmorItems {
    public static final Item HELMET = register("iron_man_helmet", new ArmorItem(ModArmorMaterial.IRON_MAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item CHESTPLATE = register("iron_man_chestplate", new ArmorItem(ModArmorMaterial.IRON_MAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item LEGGINGS = register("iron_man_leggings", new ArmorItem(ModArmorMaterial.IRON_MAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item BOOTS = register("iron_man_boots", new ArmorItem(ModArmorMaterial.IRON_MAN, ArmorItem.Type.BOOTS, new Item.Properties()));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(IronmanMod.MOD_ID, name), item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.world.item.CreativeModeTabs.COMBAT).register(entries -> {
            entries.accept(HELMET); entries.accept(CHESTPLATE); entries.accept(LEGGINGS); entries.accept(BOOTS);
        });
    }
    private ModArmorItems() {}
}