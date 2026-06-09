package com.cozary.ore_creeper.entities;

import com.cozary.ore_creeper.data.BaseOreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperManager;
import com.cozary.ore_creeper.init.ModTags;
import com.cozary.ore_creeper.particles.ColoredParticleOptions;
import com.cozary.ore_creeper.util.ExplosionTypes;
import com.cozary.ore_creeper.util.IOreExplosionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.joml.Vector3f;

import java.util.Optional;

public class BaseOreCreeperEntity extends Creeper {

    private static final EntityDataAccessor<String> BASE_ID = SynchedEntityData.defineId(BaseOreCreeperEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> TEXTURE_LOCATION = SynchedEntityData.defineId(BaseOreCreeperEntity.class, EntityDataSerializers.STRING);

    private static final IOreExplosionConfig FALLBACK = new IOreExplosionConfig() {
        @Override
        public Block getOreBlock() {
            return null;
        }

        @Override
        public Block getRawBlock() {
            return null;
        }

        @Override
        public Block getDeepslateOreBlock() {
            return null;
        }

        @Override
        public float getRadius() {
            return 3.0f;
        }

        @Override
        public float getOreChance() {
            return 1.0f; // Default to 100% chance for fallback
        }

        @Override
        public float getRawChance() {
            return 0.0f; // Default to 0% raw chance for fallback
        }

        @Override
        public Identifier getTextureId() {
            return Identifier.fromNamespaceAndPath("minecraft", "textures/entity/creeper/creeper.png");
        }

        @Override
        public int getMinSpawnYLevel() {
            return 0;
        }

        @Override
        public int getMaxSpawnYLevel() {
            return 320;
        }
    };

    public BaseOreCreeperEntity(EntityType<? extends BaseOreCreeperEntity> type, Level level) {
        super(type, level);
    }

    public static boolean checkSpawnRules(EntityType<? extends BaseOreCreeperEntity> creeper, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        Identifier entityId = BuiltInRegistries.ENTITY_TYPE.getKey(creeper);
        BaseOreCreeper base = BaseOreCreeperManager.getType(entityId);

        if (base != null) {
            return pos.getY() < base.getMaxSpawnYLevel() && pos.getY() > base.getMinSpawnYLevel() && world.getBlockState(pos.below()).is(base.isNether() ? ModTags.SPAWNABLE_BLOCKS_NETHER : ModTags.SPAWNABLE_BLOCKS);
        }

        return checkMobSpawnRules(creeper, world, reason, pos, random);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BASE_ID, "");
        builder.define(TEXTURE_LOCATION, "");
    }

    public void setBase(Identifier baseId) {
        this.entityData.set(BASE_ID, baseId.toString());
        BaseOreCreeper base = BaseOreCreeperManager.getType(baseId);
        if (base != null) {
            this.entityData.set(TEXTURE_LOCATION, base.texture().toString());
        }
    }

    public Identifier getBaseId() {
        String id = this.entityData.get(BASE_ID);
        return id.isEmpty() ? null : Identifier.tryParse(id);
    }

    public Identifier getTextureLocation() {
        String tex = this.entityData.get(TEXTURE_LOCATION);
        return tex.isEmpty() ? FALLBACK.getTextureId() : Identifier.tryParse(tex);
    }

    private IOreExplosionConfig getBaseConfig() {
        Identifier id = getBaseId();
        if (id != null) {
            BaseOreCreeper base = BaseOreCreeperManager.getType(id);
            if (base != null) {
                return new IOreExplosionConfig() {
                    @Override
                    public Block getOreBlock() {
                        return base.getOreBlock();
                    }

                    @Override
                    public Block getRawBlock() {
                        return base.getRawBlock();
                    }

                    @Override
                    public Block getDeepslateOreBlock() {
                        return base.getDeepslateOreBlock();
                    }

                    @Override
                    public float getRadius() {
                        return base.getRadius();
                    }

                    @Override
                    public float getOreChance() {
                        return base.getOreChance();
                    }

                    @Override
                    public float getRawChance() {
                        return base.getRawChance();
                    }

                    @Override
                    public Identifier getTextureId() {
                        return getTextureLocation();
                    }

                    @Override
                    public int getParticleColor() {
                        return base.getParticleColor();
                    }

                    @Override
                    public Optional<Integer> getSecondaryParticleColor() {
                        return base.getSecondaryParticleColor();
                    }

                    @Override
                    public boolean isNether() {
                        return base.isNether();
                    }

                    @Override
                    public int getMinSpawnYLevel() {
                        return base.getMinSpawnYLevel();
                    }

                    @Override
                    public int getMaxSpawnYLevel() {
                        return base.getMaxSpawnYLevel();
                    }
                };
            }
        }
        return FALLBACK;
    }

    public IOreExplosionConfig getOreType() {
        return getBaseConfig();
    }

    @Override
    public void explodeCreeper() {
        if (!this.level().isClientSide()) {
            this.dead = true;
            IOreExplosionConfig config = getOreType();
            if (isNetherCreeper()) {
                ExplosionTypes.netherExplosionEffect(this, this.level(), this.getX(), this.getY(), this.getZ(), config);
            } else {
                ExplosionTypes.oreExplosionEffect(this, this.level(), this.getX(), this.getY(), this.getZ(), config);
            }

            spawnParticles(getExplosionParticle());
            if (getSecondaryExplosionParticle() != null) {
                spawnParticles(getSecondaryExplosionParticle());
            }

            this.discard();
            this.spawnLingeringCloud();
        }
    }

    protected ParticleOptions getExplosionParticle() {
        int color = 0xFFFFFF;
        Identifier id = getBaseId();
        if (id != null) {
            BaseOreCreeper base = BaseOreCreeperManager.getType(id);
            if (base != null) {
                color = base.particleColor();
            }
        }

        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        return new ColoredParticleOptions(new Vector3f(r, g, b));
    }

    protected ParticleOptions getSecondaryExplosionParticle() {
        Identifier id = getBaseId();
        if (id != null) {
            BaseOreCreeper base = BaseOreCreeperManager.getType(id);
            if (base != null && base.secondaryParticleColor().isPresent()) {
                int color = base.secondaryParticleColor().get();
                float r = (float) (color >> 16 & 255) / 255.0F;
                float g = (float) (color >> 8 & 255) / 255.0F;
                float b = (float) (color & 255) / 255.0F;
                return new ColoredParticleOptions(new Vector3f(r, g, b));
            }
        }
        return null;
    }

    protected void spawnParticles(ParticleOptions particle) {
        if (particle == null) return;

        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        ((ServerLevel) this.level()).sendParticles(particle, this.getX() + 0.5, this.getY(), this.getZ() + 0.5, isNetherCreeper() ? 250 : 500, d1, d2, d0, 0.5);
    }

    protected boolean isNetherCreeper() {
        Identifier id = getBaseId();
        if (id != null) {
            BaseOreCreeper base = BaseOreCreeperManager.getType(id);
            if (base != null) {
                return base.isNether();
            }
        }
        return false;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        String base = this.entityData.get(BASE_ID);
        if (!base.isEmpty()) {
            valueOutput.putString("Base", base);
        }
        String texture = this.entityData.get(TEXTURE_LOCATION);
        if (!texture.isEmpty()) {
            valueOutput.putString("Texture", texture);
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        input.getString("Base").ifPresent(base -> this.entityData.set(BASE_ID, base));
        input.getString("Texture").ifPresent(texture -> this.entityData.set(TEXTURE_LOCATION, texture));

        if (this.entityData.get(TEXTURE_LOCATION).isEmpty() && !this.entityData.get(BASE_ID).isEmpty()) {
            Identifier id = Identifier.tryParse(this.entityData.get(BASE_ID));
            if (id != null) {
                BaseOreCreeper base = BaseOreCreeperManager.getType(id);
                if (base != null) {
                    this.entityData.set(TEXTURE_LOCATION, base.texture().toString());
                }
            }
        }
    }
}
