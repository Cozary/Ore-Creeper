package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModSpawnEggs {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<Item>> SPAWNEGGS_TAB = Sets.newLinkedHashSet();

    public static final Supplier<Item> COAL_CREEPER_EGG = registerSpawnEgg("coal_creeper_spawn_egg", ModEntityTypes.COAL_CREEPER);
    public static final Supplier<Item> COPPER_CREEPER_EGG = registerSpawnEgg("copper_creeper_spawn_egg", ModEntityTypes.COPPER_CREEPER);
    public static final Supplier<Item> DIAMOND_CREEPER_EGG = registerSpawnEgg("diamond_creeper_spawn_egg", ModEntityTypes.DIAMOND_CREEPER);
    public static final Supplier<Item> EMERALD_CREEPER_EGG = registerSpawnEgg("emerald_creeper_spawn_egg", ModEntityTypes.EMERALD_CREEPER);
    public static final Supplier<Item> GOLD_CREEPER_EGG = registerSpawnEgg("gold_creeper_spawn_egg", ModEntityTypes.GOLD_CREEPER);
    public static final Supplier<Item> IRON_CREEPER_EGG = registerSpawnEgg("iron_creeper_spawn_egg", ModEntityTypes.IRON_CREEPER);
    public static final Supplier<Item> LAPIS_LAZULI_CREEPER_EGG = registerSpawnEgg("lapis_lazuli_creeper_spawn_egg", ModEntityTypes.LAPIS_LAZULI_CREEPER);
    public static final Supplier<Item> NETHER_GOLD_CREEPER_EGG = registerSpawnEgg("nether_gold_creeper_spawn_egg", ModEntityTypes.NETHER_GOLD_CREEPER);
    public static final Supplier<Item> NETHER_QUARTZ_CREEPER_EGG = registerSpawnEgg("nether_quartz_creeper_spawn_egg", ModEntityTypes.NETHER_QUARTZ_CREEPER);
    public static final Supplier<Item> REDSTONE_CREEPER_EGG = registerSpawnEgg("redstone_creeper_spawn_egg", ModEntityTypes.REDSTONE_CREEPER);
    public static final Supplier<Item> ANCIENT_DEBRIS_CREEPER_EGG = registerSpawnEgg("ancient_debris_creeper_spawn_egg", ModEntityTypes.ANCIENT_DEBRIS_CREEPER);

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

    public static void loadClass() {
    }
}
