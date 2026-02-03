package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = OreCreeper.MOD_ID)
public class SpawnPlacementRegister {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        LOGGER.info("SpawnPlacementRegister: Registering spawn placements...");
        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {
                LOGGER.info("Registering spawn placement for: {}", id);
                event.register(
                        (EntityType<BaseOreCreeperEntity>) type,
                        SpawnPlacementTypes.ON_GROUND,
                        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        BaseOreCreeperEntity::checkSpawnRules,
                        RegisterSpawnPlacementsEvent.Operation.AND
                );
            });
        }
    }
}
