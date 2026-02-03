package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public final class EntityRegister {

    public static void registerAttributes() {
        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {
                FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) type, BaseOreCreeperEntity.createAttributes());
            });
        }
    }
}
