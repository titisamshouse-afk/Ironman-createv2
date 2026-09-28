package com.ironman.main;

import java.util.EnumMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;

public final class ModArmorMaterial {
    public static final Holder<ArmorMaterial> IRON_MAN = Registry.registerForHolder(
        BuiltInRegistries.ARMOR_MATERIAL,
        ResourceLocation.fromNamespaceAndPath(IronmanMod.MOD_ID, "iron_man"),
        new ArmorMaterial(
        new EnumMap<>(java.util.Map.of(
            ArmorItem.Type.HELMET, 3,
            ArmorItem.Type.CHESTPLATE, 8,
            ArmorItem.Type.LEGGINGS, 6,
            ArmorItem.Type.BOOTS, 3
        )),
        30,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        () -> net.minecraft.world.item.crafting.Ingredient.of(Items.NETHERITE_INGOT),
        java.util.List.of(),
        3.0f,
        0.1f
        )
    );
    private ModArmorMaterial() {}
}