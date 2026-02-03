package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = OreCreeper.MOD_ID)
public class EntityRegister {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {

        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {
                event.put((EntityType<? extends LivingEntity>) type, BaseOreCreeperEntity.createAttributes().build());
            });
        }
    }
}
