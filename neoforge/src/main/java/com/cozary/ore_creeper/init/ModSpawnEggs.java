package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModSpawnEggs {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, OreCreeper.MOD_ID);

    public static LinkedHashSet<RegistryObject<Item>> SPAWNEGGS_TAB = Sets.newLinkedHashSet();

    public static final Supplier<Item> COAL_CREEPER_EGG = registerWithTab("coal_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.COAL_CREEPER, 0x808080, 0x000000, new Item.Properties()));
    public static final Supplier<Item> COPPER_CREEPER_EGG = registerWithTab("copper_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.COPPER_CREEPER, 0x808080, 0xd2691e, new Item.Properties()));
    public static final Supplier<Item> DIAMOND_CREEPER_EGG = registerWithTab("diamond_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.DIAMOND_CREEPER, 0x808080, 0x00bfff, new Item.Properties()));
    public static final Supplier<Item> EMERALD_CREEPER_EGG = registerWithTab("emerald_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.EMERALD_CREEPER, 0x808080, 0x7cfc00, new Item.Properties()));
    public static final Supplier<Item> GOLD_CREEPER_EGG = registerWithTab("gold_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.GOLD_CREEPER, 0x808080, 0xffd700, new Item.Properties()));
    public static final Supplier<Item> IRON_CREEPER_EGG = registerWithTab("iron_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.IRON_CREEPER, 0x808080, 0xffb6c1, new Item.Properties()));
    public static final Supplier<Item> LAPIS_LAZULI_CREEPER_EGG = registerWithTab("lapis_lazuli_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.LAPIS_LAZULI_CREEPER, 0x808080, 0x00008b, new Item.Properties()));
    public static final Supplier<Item> NETHER_GOLD_CREEPER_EGG = registerWithTab("nether_gold_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.NETHER_GOLD_CREEPER, 0x8b0000, 0xffd700, new Item.Properties()));
    public static final Supplier<Item> NETHER_QUARTZ_CREEPER_EGG = registerWithTab("nether_quartz_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.NETHER_QUARTZ_CREEPER, 0x8b0000, 0xf5f5f5, new Item.Properties()));
    public static final Supplier<Item> REDSTONE_CREEPER_EGG = registerWithTab("redstone_creeper_spawn_egg", () -> new DeferredSpawnEggItem(ModEntityTypes.REDSTONE_CREEPER, 0x808080, 0xff0000, new Item.Properties()));


    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<? extends Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        SPAWNEGGS_TAB.add(item);
        return item;
    }

    public static void loadClass() {
    }
}
