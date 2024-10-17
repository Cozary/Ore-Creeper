package com.cozary.ore_creeper;

import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import fuzs.forgeconfigapiport.forge.api.neoforge.v4.NeoForgeConfigRegistry;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperForge {

    public OreCreeperForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        OreCreeper.init();
        ModTabs.CREATIVE_MODE_TAB.register(eventBus);
        ModSpawnEggs.loadClass();

        NeoForgeConfigRegistry.INSTANCE.register(ModConfig.Type.COMMON, ConfigurationHandler.spec);
    }
}