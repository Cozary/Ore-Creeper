package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.EmeraldCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EmeraldCreeperRenderer extends BaseCreeperRenderer<EmeraldCreeperEntity> {
    public EmeraldCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.EMERALD_CREEPER, "emerald_creeper");
    }
}
