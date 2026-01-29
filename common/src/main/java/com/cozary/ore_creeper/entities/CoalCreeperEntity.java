package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.config.CommonConfigManager;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.util.ExplosionTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class CoalCreeperEntity extends AbstractOreCreeperEntity {

    public CoalCreeperEntity(EntityType<? extends AbstractOreCreeperEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected ExplosionTypes.OreType getOreType() {
        return ExplosionTypes.OreType.COAL;
    }

    @Override
    protected ParticleOptions getExplosionParticle() {
        return ParticleList.COAL_EXPLOSION.get();
    }

    public static boolean canOreCreeperSpawn(EntityType<? extends AbstractOreCreeperEntity> creeper, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return checkSpawnRules(creeper, world, reason, pos, random, CommonConfigManager.getConfig().coalCreeperMaxSpawnYLevel(), false);
    }
}
