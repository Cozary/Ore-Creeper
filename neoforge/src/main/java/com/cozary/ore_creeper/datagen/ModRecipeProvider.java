package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.init.ModBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
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
                .save(this.output);
    }
}
