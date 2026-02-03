package com.cozary.ore_creeper.util;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public interface IOreExplosionConfig {
    Block getOreBlock();

    Block getRawBlock();

    Block getDeepslateOreBlock();

    float getRadius();

    Identifier getTextureId();

    default int getParticleColor() {
        return 0xFFFFFF;
    }

    default Optional<Integer> getSecondaryParticleColor() {
        return Optional.empty();
    }

    default boolean isNether() {
        return false;
    }

    int getMaxSpawnYLevel();
}
