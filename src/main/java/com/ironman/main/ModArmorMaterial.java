package com.ironman.main;

import java.util.EnumMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;

public final class ModArmorMaterial {
    public static final ArmorMaterial IRON_MAN = new ArmorMaterial(
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
    );
    private ModArmorMaterial() {}
}