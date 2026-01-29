package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.CoalCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CoalCreeperRenderer extends BaseCreeperRenderer<CoalCreeperEntity> {
    public CoalCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.COAL_CREEPER, "coal_creeper");
    }
}
