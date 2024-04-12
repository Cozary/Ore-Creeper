package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.entities.CoalCreeperEntity;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public final class EntityRegister implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricDefaultAttributeRegistry.register(ModEntityTypes.COAL_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.COPPER_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DIAMOND_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.EMERALD_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.GOLD_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.IRON_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.REDSTONE_CREEPER.get(), CoalCreeperEntity.createAttributes());
    }
}
