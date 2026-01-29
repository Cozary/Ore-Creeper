package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.config.CommonConfigManager;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.util.ExplosionTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class NetherQuartzCreeperEntity extends AbstractOreCreeperEntity {

    public NetherQuartzCreeperEntity(EntityType<? extends Creeper> type, Level level) {
        super(type, level);
    }

    @Override
    protected ExplosionTypes.OreType getOreType() {
        return ExplosionTypes.OreType.NETHERQUARTZ;
    }

    @Override
    protected ParticleOptions getExplosionParticle() {
        return ParticleList.WHITE_EXPLOSION.get();
    }

    @Override
    protected ParticleOptions getSecondaryExplosionParticle() {
        return ParticleList.REDSTONE_EXPLOSION.get();
    }

    @Override
    protected boolean isNetherCreeper() {
        return true;
    }

    public static boolean canOreCreeperSpawn(EntityType<? extends AbstractOreCreeperEntity> creeper, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return checkSpawnRules(creeper, world, reason, pos, random, CommonConfigManager.getConfig().netherQuartzCreeperMaxSpawnYLevel(), true);
    }
}
