package com.cozary.ore_creeper;

import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperForge {

    public OreCreeperForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        OreCreeper.init();
        ModTabs.CREATIVE_MODE_TAB.register(eventBus);
        ModSpawnEggs.loadClass();
    }
}