package com.ironman.main;

import net.fabricmc.api.ModInitializer;

public class IronmanMod implements ModInitializer {
    public static final String MOD_ID = "ironman";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModArmorItems.initialize();
        IronmanEnergy.initialize();
        IronmanPlayerData.initialize();
        IronmanNetworking.initialize();
    }
}
