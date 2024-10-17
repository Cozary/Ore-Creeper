package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

@EventBusSubscriber(modid = OreCreeper.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class SpawnPlacementRegister {

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntityTypes.COAL_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoalCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.COPPER_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CopperCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.DIAMOND_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DiamondCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.EMERALD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EmeraldCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.GOLD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.IRON_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LapisLazuliCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherGoldCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherQuartzCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(ModEntityTypes.REDSTONE_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RedstoneCreeperEntity::canOreCreeperSpawn, SpawnPlacementRegisterEvent.Operation.AND);

    }
}
