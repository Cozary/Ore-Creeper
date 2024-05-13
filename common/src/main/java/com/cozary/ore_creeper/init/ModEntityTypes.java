package com.cozary.ore_creeper.init;


import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.entities.*;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModEntityTypes {

    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<EntityType<?>>> ENTITY_LIST = Sets.newLinkedHashSet();

    public static final RegistryObject<EntityType<CoalCreeperEntity>> COAL_CREEPER = registerEntitiesList("coal_creeper", () -> EntityType.Builder.of(CoalCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "coal_creeper").toString()));
    public static final RegistryObject<EntityType<CopperCreeperEntity>> COPPER_CREEPER = registerEntitiesList("copper_creeper", () -> EntityType.Builder.of(CopperCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "copper_creeper").toString()));
    public static final RegistryObject<EntityType<DiamondCreeperEntity>> DIAMOND_CREEPER = registerEntitiesList("diamond_creeper", () -> EntityType.Builder.of(DiamondCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "diamond_creeper").toString()));
    public static final RegistryObject<EntityType<EmeraldCreeperEntity>> EMERALD_CREEPER = registerEntitiesList("emerald_creeper", () -> EntityType.Builder.of(EmeraldCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "emerald_creeper").toString()));
    public static final RegistryObject<EntityType<GoldCreeperEntity>> GOLD_CREEPER = registerEntitiesList("gold_creeper", () -> EntityType.Builder.of(GoldCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "gold_creeper").toString()));
    public static final RegistryObject<EntityType<IronCreeperEntity>> IRON_CREEPER = registerEntitiesList("iron_creeper", () -> EntityType.Builder.of(IronCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "iron_creeper").toString()));
    public static final RegistryObject<EntityType<LapisLazuliCreeperEntity>> LAPIS_LAZULI_CREEPER = registerEntitiesList("lapis_lazuli_creeper", () -> EntityType.Builder.of(LapisLazuliCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "lapis_lazuli_creeper").toString()));
    public static final RegistryObject<EntityType<NetherGoldCreeperEntity>> NETHER_GOLD_CREEPER = registerEntitiesList("nether_gold_creeper", () -> EntityType.Builder.of(NetherGoldCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "nether_gold_creeper").toString()));
    public static final RegistryObject<EntityType<NetherQuartzCreeperEntity>> NETHER_QUARTZ_CREEPER = registerEntitiesList("nether_quartz_creeper", () -> EntityType.Builder.of(NetherQuartzCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "nether_quartz_creeper").toString()));
    public static final RegistryObject<EntityType<RedstoneCreeperEntity>> REDSTONE_CREEPER = registerEntitiesList("redstone_creeper", () -> EntityType.Builder.of(RedstoneCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.7F).clientTrackingRange(8)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "redstone_creeper").toString()));
    public static final RegistryObject<EntityType<OrePrimedTnt>> ORE_PRIMED_TNT = ENTITY_TYPES.register("ore_primed_tnt", () -> EntityType.Builder.<OrePrimedTnt>of(OrePrimedTnt::new, MobCategory.MISC)
            .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
            .build(new ResourceLocation(OreCreeper.MOD_ID, "ore_primed_tnt").toString()));

    @SuppressWarnings("unchecked")
    public static <T extends EntityType<?>> RegistryObject<T> registerEntitiesList(final String name, final Supplier<? extends T> supplier) {
        RegistryObject<T> entity = ENTITY_TYPES.register(name, supplier);
        ENTITY_LIST.add((RegistryObject<EntityType<?>>) entity);
        return entity;
    }

    public static void loadClass() {
    }

}
