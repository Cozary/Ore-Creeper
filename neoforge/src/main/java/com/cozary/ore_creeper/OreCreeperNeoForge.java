package com.cozary.ore_creeper;


import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperNeoForge {

    public OreCreeperNeoForge(IEventBus eventBus, ModContainer container) {
        OreCreeper.init();
        ModTabs.init(eventBus);
        ModSpawnEggs.loadClass();
    }

}