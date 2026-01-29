package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.DiamondCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DiamondCreeperRenderer extends BaseCreeperRenderer<DiamondCreeperEntity> {
    public DiamondCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.DIAMOND_CREEPER, "diamond_creeper");
    }
}
