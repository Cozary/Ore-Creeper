package com.cozary.ore_creeper.init;


import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import com.cozary.ore_creeper.entities.OrePrimedTnt;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;

public class ModEntityTypes {

    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, OreCreeper.MOD_ID);
    public static final Map<String, RegistryObject<EntityType<?>>> ENTITY_MAP = new HashMap<>();
    public static LinkedHashSet<RegistryObject<EntityType<?>>> ENTITY_LIST = Sets.newLinkedHashSet();

    @SuppressWarnings("unchecked")
    public static <T extends EntityType<?>> void registerEntitiesList(final String name, final Supplier<? extends T> supplier) {
        RegistryObject<T> entity = ENTITY_TYPES.register(name, supplier);
        ENTITY_LIST.add((RegistryObject<EntityType<?>>) entity);
        ENTITY_MAP.put(name, (RegistryObject<EntityType<?>>) entity);
    }    public static final RegistryObject<EntityType<OrePrimedTnt>> ORE_PRIMED_TNT = ENTITY_TYPES.register("ore_primed_tnt", () -> EntityType.Builder.<OrePrimedTnt>of(OrePrimedTnt::new, MobCategory.MISC)
            .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_primed_tnt"))));

    private static <T extends Entity> void registerCreeper(String name, EntityType.EntityFactory<T> factory) {
        registerEntitiesList(name, () -> EntityType.Builder.of(factory, MobCategory.MONSTER)
                .sized(0.6F, 1.7F).clientTrackingRange(8)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, name))));
    }

    public static void loadClass() {
        BaseOreCreeperLoader.loadTypes();

        for (Map.Entry<Identifier, BaseOreCreeper> entry : BaseOreCreeperLoader.LOADED_TYPES.entrySet()) {
            String name = entry.getKey().getPath();

            registerCreeper(name, (EntityType<BaseOreCreeperEntity> type, Level level) -> {
                BaseOreCreeperEntity entity = new BaseOreCreeperEntity(type, level);
                entity.setBase(entry.getKey());
                return entity;
            });
        }
    }


}
