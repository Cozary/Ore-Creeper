package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.client.render.BaseOreCreeperRenderer;
import com.cozary.ore_creeper.client.render.OreTntRenderer;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import com.cozary.ore_creeper.init.ModEntityTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.cozary.ore_creeper.util.ClientEventBusSubscriber.ORE_CREEPER_BASE;

@EventBusSubscriber(modid = OreCreeper.MOD_ID, value = Dist.CLIENT)
public class RendererRegister {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ORE_PRIMED_TNT.get(), OreTntRenderer::new);

        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {

                event.registerEntityRenderer((EntityType<BaseOreCreeperEntity>) type, BaseOreCreeperRenderer::new);

            });
        }
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ORE_CREEPER_BASE, OreCreeperModel::createBodyLayer);
    }

}
