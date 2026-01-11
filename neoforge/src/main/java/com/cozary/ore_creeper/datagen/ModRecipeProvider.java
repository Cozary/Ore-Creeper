package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {

        shaped(RecipeCategory.MISC, ModBlocks.ORE_TNT.get())
                .pattern("#S#")
                .pattern("STS")
                .pattern("#S#")
                .define('#', Blocks.DEEPSLATE)
                .define('S', Blocks.STONE)
                .define('T', Blocks.TNT)
                .unlockedBy("has_tnt", has(Blocks.TNT))
                .save(output);

    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Ore Creeper Recipes";
        }
    }

}