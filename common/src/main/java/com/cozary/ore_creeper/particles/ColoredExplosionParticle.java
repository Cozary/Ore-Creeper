package com.cozary.ore_creeper.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class ColoredExplosionParticle extends SingleQuadParticle {

    private final SpriteSet spriteSet;

    protected ColoredExplosionParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.first());
        this.gravity = -0.1F;
        this.friction = 0.9F;
        this.spriteSet = spriteSet;
        this.xd = xSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
        this.yd = ySpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
        this.zd = zSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
        float f = this.random.nextFloat() * 0.3F + 0.7F;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;
        this.quadSize = 0.1F * (this.random.nextFloat() * this.random.nextFloat() * 6.0F + 1.0F);
        this.lifetime = (int) (16.0D / ((double) this.random.nextFloat() * 0.8D + 0.2D)) + 2;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteSet);
    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Factory implements ParticleProvider<ColoredParticleOptions> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(ColoredParticleOptions type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            ColoredExplosionParticle particle = new ColoredExplosionParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
            particle.setColor(type.color().x, type.color().y, type.color().z);
            return particle;
        }
    }
}