package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.NetherGoldCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class NetherGoldCreeperRenderer extends BaseCreeperRenderer<NetherGoldCreeperEntity> {
    public NetherGoldCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.NETHER_GOLD_CREEPER, "nether_gold_creeper");
    }
}
