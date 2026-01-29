package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.init.ModTags;
import com.cozary.ore_creeper.util.ExplosionTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractOreCreeperEntity extends Creeper {

    public AbstractOreCreeperEntity(EntityType<? extends Creeper> type, Level level) {
        super(type, level);
    }

    protected abstract ExplosionTypes.OreType getOreType();
    protected abstract ParticleOptions getExplosionParticle();
    protected ParticleOptions getSecondaryExplosionParticle() { return null; }
    protected boolean isNetherCreeper() { return false; }

    @Override
    public void explodeCreeper() {
        if (!this.level().isClientSide()) {
            this.dead = true;
            ExplosionTypes explosionTypes = new ExplosionTypes();
            if (isNetherCreeper()) {
                explosionTypes.netherExplosionEffect(this, this.level(), this.getX(), this.getY(), this.getZ(), getOreType());
            } else {
                explosionTypes.oreExplosionEffect(this, this.level(), this.getX(), this.getY(), this.getZ(), getOreType());
            }
            
            spawnParticles(getExplosionParticle());
            if (getSecondaryExplosionParticle() != null) {
                spawnParticles(getSecondaryExplosionParticle());
            }
            
            this.discard();
            this.spawnLingeringCloud();
        }
    }

    private void spawnParticles(ParticleOptions particle) {
        if (particle == null) return;
        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        ((ServerLevel) this.level()).sendParticles(particle, this.getX() + 0.5, this.getY(), this.getZ() + 0.5, isNetherCreeper() ? 250 : 500, d1, d2, d0, 0.5);
    }

    public static boolean checkSpawnRules(EntityType<? extends AbstractOreCreeperEntity> creeper, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random, int maxY, boolean isNether) {
        boolean yCheck = pos.getY() < maxY;
        boolean blockCheck = isNether 
            ? world.getBlockState(pos.below()).is(ModTags.SPAWNABLE_BLOCKS_NETHER)
            : world.getBlockState(pos.below()).is(ModTags.SPAWNABLE_BLOCKS);
        
        if (isNether) {
            return yCheck && blockCheck;
        } else {
            return yCheck && blockCheck && isDarkEnoughToSpawn(world, pos, random) && checkMobSpawnRules(creeper, world, reason, pos, random);
        }
    }
}
