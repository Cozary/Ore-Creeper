package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.client.render.*;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.cozary.ore_creeper.util.ClientEventBusSubscriber.*;

@Mod.EventBusSubscriber(modid = OreCreeper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererRegister {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.COAL_CREEPER.get(), CoalCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.COPPER_CREEPER.get(), CopperCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.DIAMOND_CREEPER.get(), DiamondCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.EMERALD_CREEPER.get(), EmeraldCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GOLD_CREEPER.get(), GoldCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.IRON_CREEPER.get(), IronCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), LapisLazuliCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.NETHER_GOLD_CREEPER.get(), NetherGoldCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), NetherQuartzCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.REDSTONE_CREEPER.get(), RedstoneCreeperRenderer::new);

        event.registerEntityRenderer(ModEntityTypes.ORE_PRIMED_TNT.get(), OreTntRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(COAL_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(COPPER_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(DIAMOND_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(EMERALD_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(GOLD_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(IRON_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(LAPIS_LAZULI_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(NETHER_GOLD_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(NETHER_QUARTZ_CREEPER, OreCreeperModel::createBodyLayer);
        event.registerLayerDefinition(REDSTONE_CREEPER, OreCreeperModel::createBodyLayer);
    }

}
