package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.client.render.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class RendererRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.COAL_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.COAL_CREEPER.get(), CoalCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.COPPER_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.COPPER_CREEPER.get(), CopperCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.DIAMOND_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.DIAMOND_CREEPER.get(), DiamondCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.EMERALD_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.EMERALD_CREEPER.get(), EmeraldCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.GOLD_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.GOLD_CREEPER.get(), GoldCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.IRON_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.IRON_CREEPER.get(), IronCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.LAPIS_LAZULI_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), LapisLazuliCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.NETHER_GOLD_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), NetherGoldCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.NETHER_QUARTZ_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), NetherQuartzCreeperRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.REDSTONE_CREEPER, OreCreeperModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.REDSTONE_CREEPER.get(), RedstoneCreeperRenderer::new);

        EntityRendererRegistry.register(ModEntityTypes.ORE_PRIMED_TNT.get(), OreTntRenderer::new);

    }
}
