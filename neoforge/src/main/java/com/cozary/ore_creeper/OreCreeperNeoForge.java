package com.cozary.ore_creeper;


import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperNeoForge {

    public OreCreeperNeoForge(IEventBus eventBus, ModContainer container) {
        OreCreeper.init();
        ModTabs.init(eventBus);
        ModSpawnEggs.loadClass();

        //Register the config
        container.registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
    }

}