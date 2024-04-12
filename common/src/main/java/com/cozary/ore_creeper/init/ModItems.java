package com.cozary.ore_creeper.init;


import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.block.BlockItemBase;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.LinkedHashSet;
import java.util.function.Supplier;


public class ModItems {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, OreCreeper.MOD_ID);

    public static final RegistryObject<Item> ORE_TNT = ITEMS.register("ore_tnt", () -> new BlockItemBase(ModBlocks.ORE_TNT.get()));

    public static void loadClass() {
    }

}
