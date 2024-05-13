package com.cozary.ore_creeper;


import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperNeoForge {

    public OreCreeperNeoForge(IEventBus eventBus) {
        eventBus.addListener(this::setupCommon);

        OreCreeper.init();
        ModTabs.init(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
        ModSpawnEggs.loadClass();
    }

    public void setupCommon(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
                    SpawnPlacements.register(ModEntityTypes.COAL_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoalCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.COPPER_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CopperCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.DIAMOND_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DiamondCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.EMERALD_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EmeraldCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.GOLD_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.IRON_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LapisLazuliCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherGoldCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherQuartzCreeperEntity::canOreCreeperSpawn);
                    SpawnPlacements.register(ModEntityTypes.REDSTONE_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RedstoneCreeperEntity::canOreCreeperSpawn);
                }
        );
    }
}