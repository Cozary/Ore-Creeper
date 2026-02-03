package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;

public class SpawnPlacementRegister {

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {

        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {

                event.register((EntityType<BaseOreCreeperEntity>) type, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseOreCreeperEntity::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);

            });
        }
    }
}
