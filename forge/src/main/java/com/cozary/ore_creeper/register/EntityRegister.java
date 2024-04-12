package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OreCreeper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {


    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.COAL_CREEPER.get(), CoalCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.COPPER_CREEPER.get(), CopperCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.DIAMOND_CREEPER.get(), DiamondCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.EMERALD_CREEPER.get(), EmeraldCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.GOLD_CREEPER.get(), GoldCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.IRON_CREEPER.get(), IronCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), LapisLazuliCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.NETHER_GOLD_CREEPER.get(), NetherGoldCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), NetherQuartzCreeperEntity.createAttributes().build());
        event.put(ModEntityTypes.REDSTONE_CREEPER.get(), RedstoneCreeperEntity.createAttributes().build());
    }
}
