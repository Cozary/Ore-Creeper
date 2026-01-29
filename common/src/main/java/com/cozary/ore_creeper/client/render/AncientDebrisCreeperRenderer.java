package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.AncientDebrisCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AncientDebrisCreeperRenderer extends BaseCreeperRenderer<AncientDebrisCreeperEntity> {
    public AncientDebrisCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.ANCIENT_DEBRIS_CREEPER, "ancient_debris_creeper");
    }
}
