package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.CopperCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CopperCreeperRenderer extends BaseCreeperRenderer<CopperCreeperEntity> {
    public CopperCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.COPPER_CREEPER, "copper_creeper");
    }
}
