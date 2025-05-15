package com.cozary.ore_creeper;

import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperForge {

    public OreCreeperForge(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();

        OreCreeper.init();
        ModTabs.CREATIVE_MODE_TAB.register(eventBus);
        ModSpawnEggs.loadClass();
    }
}