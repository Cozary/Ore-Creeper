package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.NetherQuartzCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class NetherQuartzCreeperRenderer extends BaseCreeperRenderer<NetherQuartzCreeperEntity> {
    public NetherQuartzCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.NETHER_QUARTZ_CREEPER, "nether_quartz_creeper");
    }
}
