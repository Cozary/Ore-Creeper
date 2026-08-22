package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends net.neoforged.neoforge.common.data.BlockTagsProvider {

    public static final TagKey<Block> SPAWNABLE_BLOCKS = BlockTags.create(Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "spawnable_blocks"));
    public static final TagKey<Block> SPAWNABLE_BLOCKS_NETHER = BlockTags.create(Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "spawnable_blocks_nether"));

    public ModBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, OreCreeper.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(SPAWNABLE_BLOCKS)
                .add(
                        Blocks.STONE.builtInRegistryHolder().key(),
                        Blocks.DEEPSLATE.builtInRegistryHolder().key());

        tag(SPAWNABLE_BLOCKS_NETHER)
                .add(
                        Blocks.NETHERRACK.builtInRegistryHolder().key());

    }
}
