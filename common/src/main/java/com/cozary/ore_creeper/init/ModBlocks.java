package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.block.OreTnt;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class ModBlocks {

    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, OreCreeper.MOD_ID);

    public static final RegistryObject<Block> ORE_TNT = BLOCKS.register("ore_tnt", OreTnt::new);

    public static void loadClass() {
    }
}
