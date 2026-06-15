package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.client.render.BaseOreCreeperRenderer;
import com.cozary.ore_creeper.client.render.OreTntRenderer;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.network.CommonNetwork;
import com.cozary.ore_creeper.network.OreCreeperSyncPayload;
import com.cozary.ore_creeper.particles.ColoredExplosionParticle;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;

@Environment(EnvType.CLIENT)
public class RendererRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        try {
            PayloadTypeRegistry.clientboundPlay().register(OreCreeperSyncPayload.TYPE, OreCreeperSyncPayload.STREAM_CODEC);
        } catch (IllegalArgumentException e) {
            OreCreeper.LOG.debug("I'm already registered, ignore me :)");
        }

        ClientPlayNetworking.registerGlobalReceiver(OreCreeperSyncPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> CommonNetwork.handleBaseSync(payload, context));
        });

        ParticleProviderRegistry.getInstance().register(ParticleList.COLORED_EXPLOSION.get(), ColoredExplosionParticle.Factory::new);

        ModelLayerRegistry.registerModelLayer(ClientEventBusSubscriber.ORE_CREEPER_BASE, OreCreeperModel::createBodyLayer);

        EntityRenderers.register(ModEntityTypes.ORE_PRIMED_TNT.get(), OreTntRenderer::new);

        for (Identifier id : BaseOreCreeperLoader.LOADED_TYPES.keySet()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(id).ifPresent(type -> {
                if (type != EntityType.PIG) {
                    EntityRenderers.register((EntityType<BaseOreCreeperEntity>) type, BaseOreCreeperRenderer::new);
                }
            });
        }

    }
}
