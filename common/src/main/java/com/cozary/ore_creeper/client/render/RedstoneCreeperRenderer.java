package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.RedstoneCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class RedstoneCreeperRenderer extends BaseCreeperRenderer<RedstoneCreeperEntity> {
    public RedstoneCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.REDSTONE_CREEPER, "redstone_creeper");
    }
}
