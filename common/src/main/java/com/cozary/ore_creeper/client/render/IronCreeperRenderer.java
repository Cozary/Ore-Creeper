package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.IronCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class IronCreeperRenderer extends BaseCreeperRenderer<IronCreeperEntity> {
    public IronCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.IRON_CREEPER, "iron_creeper");
    }
}
