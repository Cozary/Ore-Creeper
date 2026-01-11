package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModSpawnEggs {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<Item>> SPAWNEGGS_TAB = Sets.newLinkedHashSet();

    public static final Supplier<Item> COAL_CREEPER_EGG = registerWithTab("coal_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.COAL_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "coal_creeper_spawn_egg")))
    ));
    public static final Supplier<Item> COPPER_CREEPER_EGG = registerWithTab("copper_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.COPPER_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "copper_creeper_spawn_egg")))));
    public static final Supplier<Item> DIAMOND_CREEPER_EGG = registerWithTab("diamond_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.DIAMOND_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "diamond_creeper_spawn_egg")))));
    public static final Supplier<Item> EMERALD_CREEPER_EGG = registerWithTab("emerald_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.COAL_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "emerald_creeper_spawn_egg")))));
    public static final Supplier<Item> GOLD_CREEPER_EGG = registerWithTab("gold_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.GOLD_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "gold_creeper_spawn_egg")))));
    public static final Supplier<Item> IRON_CREEPER_EGG = registerWithTab("iron_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.IRON_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "iron_creeper_spawn_egg")))));
    public static final Supplier<Item> LAPIS_LAZULI_CREEPER_EGG = registerWithTab("lapis_lazuli_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.LAPIS_LAZULI_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "lapis_lazuli_creeper_spawn_egg")))));
    public static final Supplier<Item> NETHER_GOLD_CREEPER_EGG = registerWithTab("nether_gold_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.NETHER_GOLD_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "nether_gold_creeper_spawn_egg")))));
    public static final Supplier<Item> NETHER_QUARTZ_CREEPER_EGG = registerWithTab("nether_quartz_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.NETHER_QUARTZ_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "nether_quartz_creeper_spawn_egg")))));
    public static final Supplier<Item> REDSTONE_CREEPER_EGG = registerWithTab("redstone_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.REDSTONE_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "redstone_creeper_spawn_egg")))));
    public static final Supplier<Item> ANCIENT_DEBRIS_CREEPER_EGG = registerWithTab("ancient_debris_creeper_spawn_egg", () -> new SpawnEggItem(
            new Item.Properties()
                    .spawnEgg(ModEntityTypes.ANCIENT_DEBRIS_CREEPER.get())
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ancient_debris_creeper_spawn_egg")))));

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<? extends Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        SPAWNEGGS_TAB.add(item);
        return item;
    }

    public static void loadClass() {
    }
}
