package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class EntityRegister {

    public static void addEntityAttributes(EntityAttributeCreationEvent event) {

        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {
                event.put((EntityType<? extends LivingEntity>) type, BaseOreCreeperEntity.createAttributes().build());
            });
        }
    }
}
