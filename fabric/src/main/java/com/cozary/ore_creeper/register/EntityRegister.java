package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public final class EntityRegister {

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntityTypes.COAL_CREEPER.get(), CoalCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.COPPER_CREEPER.get(), CopperCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DIAMOND_CREEPER.get(), DiamondCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.EMERALD_CREEPER.get(), EmeraldCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.GOLD_CREEPER.get(), GoldCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.IRON_CREEPER.get(), IronCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), LapisLazuliCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), NetherGoldCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), NetherQuartzCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.REDSTONE_CREEPER.get(), RedstoneCreeperEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.ANCIENT_DEBRIS_CREEPER.get(), AncientDebrisCreeperEntity.createAttributes());
    }
}
