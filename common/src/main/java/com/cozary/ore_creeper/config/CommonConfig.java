package com.cozary.ore_creeper.config;

import com.google.gson.annotations.SerializedName;

public class CommonConfig {

    @SerializedName("oreCreepersExplodeLikeNormalCreepers")
    private boolean oreCreepersExplodeLikeNormalCreepers = false;
    @SerializedName("oreTntExplosionRadius")
    private float oreTntExplosionRadius = 4.0f;

    public boolean oreCreepersExplodeLikeNormalCreepers() {
        return oreCreepersExplodeLikeNormalCreepers;
    }

    public float oreTntExplosionRadius() {
        return oreTntExplosionRadius;
    }
}
