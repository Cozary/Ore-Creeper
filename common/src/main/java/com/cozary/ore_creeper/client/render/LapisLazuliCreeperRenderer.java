package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.entities.LapisLazuliCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LapisLazuliCreeperRenderer extends BaseCreeperRenderer<LapisLazuliCreeperEntity> {
    public LapisLazuliCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, ClientEventBusSubscriber.LAPIS_LAZULI_CREEPER, "lapis_lazuli_creeper");
    }
}
