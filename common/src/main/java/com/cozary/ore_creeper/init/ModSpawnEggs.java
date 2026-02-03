package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;

public class ModSpawnEggs {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<Item>> SPAWNEGGS_TAB = Sets.newLinkedHashSet();

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<? extends Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        SPAWNEGGS_TAB.add(item);
        return item;
    }

    private static Supplier<Item> registerSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> type) {
        return registerWithTab(name, () -> new SpawnEggItem(
                new Item.Properties()
                        .spawnEgg(type.get())
                        .setId(ResourceKey.create(Registries.ITEM,
                                Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, name)))
        ));
    }

    @SuppressWarnings("unchecked")
    public static void loadClass() {
        for (Map.Entry<Identifier, com.cozary.ore_creeper.data.BaseOreCreeper> entry : BaseOreCreeperLoader.LOADED_TYPES.entrySet()) {
            String id = entry.getKey().getPath();
            String entityName = id;

            RegistryObject<EntityType<?>> entityObj = ModEntityTypes.ENTITY_MAP.get(entityName);
            if (entityObj != null) {
                registerSpawnEgg(entityName + "_spawn_egg", () -> (EntityType<? extends Mob>) entityObj.get());
            } else {
                OreCreeper.LOG.error("Could not find entity type for ore creeper spawn egg: {}", entityName);
            }
        }
    }
}
