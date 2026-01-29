package com.cozary.ore_creeper.init;


import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.entities.*;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModEntityTypes {

    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<EntityType<?>>> ENTITY_LIST = Sets.newLinkedHashSet();

    public static final RegistryObject<EntityType<CoalCreeperEntity>> COAL_CREEPER = registerCreeper("coal_creeper", CoalCreeperEntity::new);
    public static final RegistryObject<EntityType<CopperCreeperEntity>> COPPER_CREEPER = registerCreeper("copper_creeper", CopperCreeperEntity::new);
    public static final RegistryObject<EntityType<DiamondCreeperEntity>> DIAMOND_CREEPER = registerCreeper("diamond_creeper", DiamondCreeperEntity::new);
    public static final RegistryObject<EntityType<EmeraldCreeperEntity>> EMERALD_CREEPER = registerCreeper("emerald_creeper", EmeraldCreeperEntity::new);
    public static final RegistryObject<EntityType<GoldCreeperEntity>> GOLD_CREEPER = registerCreeper("gold_creeper", GoldCreeperEntity::new);
    public static final RegistryObject<EntityType<IronCreeperEntity>> IRON_CREEPER = registerCreeper("iron_creeper", IronCreeperEntity::new);
    public static final RegistryObject<EntityType<LapisLazuliCreeperEntity>> LAPIS_LAZULI_CREEPER = registerCreeper("lapis_lazuli_creeper", LapisLazuliCreeperEntity::new);
    public static final RegistryObject<EntityType<NetherGoldCreeperEntity>> NETHER_GOLD_CREEPER = registerCreeper("nether_gold_creeper", NetherGoldCreeperEntity::new);
    public static final RegistryObject<EntityType<NetherQuartzCreeperEntity>> NETHER_QUARTZ_CREEPER = registerCreeper("nether_quartz_creeper", NetherQuartzCreeperEntity::new);
    public static final RegistryObject<EntityType<RedstoneCreeperEntity>> REDSTONE_CREEPER = registerCreeper("redstone_creeper", RedstoneCreeperEntity::new);
    public static final RegistryObject<EntityType<AncientDebrisCreeperEntity>> ANCIENT_DEBRIS_CREEPER = registerCreeper("ancient_debris_creeper", AncientDebrisCreeperEntity::new);

    public static final RegistryObject<EntityType<OrePrimedTnt>> ORE_PRIMED_TNT = ENTITY_TYPES.register("ore_primed_tnt", () -> EntityType.Builder.<OrePrimedTnt>of(OrePrimedTnt::new, MobCategory.MISC)
            .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_primed_tnt"))));

    @SuppressWarnings("unchecked")
    public static <T extends EntityType<?>> RegistryObject<T> registerEntitiesList(final String name, final Supplier<? extends T> supplier) {
        RegistryObject<T> entity = ENTITY_TYPES.register(name, supplier);
        ENTITY_LIST.add((RegistryObject<EntityType<?>>) entity);
        return entity;
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerCreeper(String name, EntityType.EntityFactory<T> factory) {
        return registerEntitiesList(name, () -> EntityType.Builder.of(factory, MobCategory.MONSTER)
                .sized(0.6F, 1.7F).clientTrackingRange(8)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, name))));
    }

    public static void loadClass() {
    }
}
