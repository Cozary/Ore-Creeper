package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.config.CommonConfigManager;
import com.cozary.ore_creeper.init.ModTags;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import com.cozary.ore_creeper.util.ExplosionTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class AncientDebrisCreeperEntity extends AbstractOreCreeperEntity {

    public AncientDebrisCreeperEntity(EntityType<? extends AbstractOreCreeperEntity> type, Level level) {
        super(type, level);
    }


    public static boolean canOreCreeperSpawn(EntityType<? extends AbstractOreCreeperEntity> creeper, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return pos.getY() < CommonConfigManager.getConfig().ancientDebrisCreeperMaxSpawnYLevel() && world.getBlockState(pos.below()).is(ModTags.SPAWNABLE_BLOCKS_NETHER);
    }

    @Override
    public void explodeCreeper() {
        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        if (!this.level().isClientSide) {
            this.dead = true;
            new ExplosionTypes().netherExplosionEffect(this, this.level(), this.getX(), this.getY(), this.getZ(), ExplosionTypes.OreType.ANCIENT_DEBRIS);
            ((ServerLevel) this.getCommandSenderWorld()).sendParticles(ParticleList.ANCIENT_DEBRIS_EXPLOSION.get(), this.getX() + 0.5, this.getY(), this.getZ() + 0.5, 250, d1, d2, d0, 0.5);
            this.discard();
            this.spawnLingeringCloud();
        }

    }

}
