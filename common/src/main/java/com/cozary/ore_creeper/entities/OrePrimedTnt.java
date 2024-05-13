package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.RegistryObject;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.cozary.ore_creeper.init.ModEntityTypes.ENTITY_LIST;

public class OrePrimedTnt extends PrimedTnt {
    Random random = new Random();
    @Nullable
    private LivingEntity owner;

    public OrePrimedTnt(EntityType<? extends PrimedTnt> p_32076_, Level p_32077_) {
        super(ModEntityTypes.ORE_PRIMED_TNT.get(), p_32077_);
    }

    public OrePrimedTnt(Level p_32079_, double p_32080_, double p_32081_, double p_32082_) {
        this(ModEntityTypes.ORE_PRIMED_TNT.get(), p_32079_);
        this.setPos(p_32080_, p_32081_, p_32082_);
        double d0 = p_32079_.random.nextDouble() * (double) ((float) Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = p_32080_;
        this.yo = p_32081_;
        this.zo = p_32082_;
    }

    public static RegistryObject<EntityType<?>> getRandomEntityType() {
        List<RegistryObject<EntityType<?>>> entityTypesList = new ArrayList<>(ENTITY_LIST);
        if (!entityTypesList.isEmpty()) {
            Random random = new Random();
            return entityTypesList.get(random.nextInt(entityTypesList.size()));
        }
        return null;
    }


    @Override
    public void explode() {
        this.level().playSound((Player) owner, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);
        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        AABB targetBox = new AABB(this.position(), this.position()).inflate(ConfigurationHandler.GENERAL.oreTntExplosionRadius.get());
        List<Creeper> foundTarget = this.level().getEntitiesOfClass(Creeper.class, targetBox);
        ((ServerLevel) this.getCommandSenderWorld()).sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.getX() + 0.5, this.getY(), this.getZ() + 0.5, 1, d1, d2, d0, 0.5);

        for (LivingEntity livingEntity : foundTarget) {
            livingEntity.remove(RemovalReason.DISCARDED);

            Entity entities = Objects.requireNonNull(getRandomEntityType()).get().create(level());

            assert entities != null;
            entities.setPos(livingEntity.position());
            level().addFreshEntity(entities);
            ((ServerLevel) this.getCommandSenderWorld()).sendParticles(ParticleTypes.POOF, entities.getX() + 0.5, entities.getY(), entities.getZ() + 0.5, 100, d1, d2, d0, 0.5);
        }
    }
}
