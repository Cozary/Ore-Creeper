package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.GoldCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GoldCreeperRenderer extends BaseCreeperRenderer<GoldCreeperEntity> {
    public GoldCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.GOLD_CREEPER, "gold_creeper");
    }
}
