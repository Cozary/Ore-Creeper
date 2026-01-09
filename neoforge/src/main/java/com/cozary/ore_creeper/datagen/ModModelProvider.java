package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.init.ModBlocks;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.RegistryObject;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, OreCreeper.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        createTntBlock(blockModels, ModBlocks.ORE_TNT.get());

        for (RegistryObject<Item> item : ModSpawnEggs.SPAWNEGGS_TAB) {
            itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
        }
    }

    private void createTntBlock(BlockModelGenerators blockModels, Block block) {
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        ResourceLocation side = this.modLocation("block/" + blockName + "_side");
        ResourceLocation top = this.modLocation("block/" + blockName + "_top");
        ResourceLocation bottom = this.modLocation("block/" + blockName + "_bottom");

        ResourceLocation modelLocation = ModelTemplates.CUBE_BOTTOM_TOP.create(
                block,
                new TextureMapping()
                        .put(TextureSlot.SIDE, side)
                        .put(TextureSlot.TOP, top)
                        .put(TextureSlot.BOTTOM, bottom)
                        .put(TextureSlot.PARTICLE, side),
                blockModels.modelOutput
        );

        MultiVariant variant = BlockModelGenerators.plainVariant(modelLocation);

        blockModels.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.UNSTABLE, variant, variant))
        );

        blockModels.registerSimpleItemModel(block, modelLocation);
    }
}
