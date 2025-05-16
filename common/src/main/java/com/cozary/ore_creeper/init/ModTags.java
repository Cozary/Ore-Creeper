package com.cozary.ore_creeper.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static com.cozary.ore_creeper.OreCreeper.MOD_ID;

public class ModTags {

    public static final TagKey<Biome> SPAWNABLE_BIOMES = TagKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "spawnable_biomes")
    );

    public static final TagKey<Biome> BLACKLIST_BIOMES = TagKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "blacklist_biomes")
    );

    public static final TagKey<Block> SPAWNABLE_BLOCKS = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "spawnable_blocks")
    );

    public static final TagKey<Biome> SPAWNABLE_BIOMES_NETHER = TagKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "spawnable_biomes_nether")
    );

    public static final TagKey<Biome> BLACKLIST_BIOMES_NETHER = TagKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "blacklist_biomes_nether")
    );

    public static final TagKey<Block> SPAWNABLE_BLOCKS_NETHER = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "spawnable_blocks_nether")
    );

    public static void loadClass() {
    }
}
