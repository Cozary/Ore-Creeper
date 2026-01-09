package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class ModWorldGenTagProvider extends BiomeTagsProvider {

    public static final TagKey<Biome> SPAWNABLE_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "spawnable_biomes"));
    public static final TagKey<Biome> SPAWNABLE_BIOMES_NETHER = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "spawnable_biomes_nether"));
    public static final TagKey<Biome> BLACKLIST_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "blacklist_biomes"));
    public static final TagKey<Biome> BLACKLIST_BIOMES_NETHER = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "blacklist_biomes_nether"));

    public ModWorldGenTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, OreCreeper.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(SPAWNABLE_BIOMES)
                .addTag(BiomeTags.IS_OVERWORLD);

        this.tag(SPAWNABLE_BIOMES_NETHER)
                .add(Biomes.NETHER_WASTES);

        this.tag(BLACKLIST_BIOMES)
                .add(Biomes.MUSHROOM_FIELDS);

        this.tag(BLACKLIST_BIOMES_NETHER);
    }
}
