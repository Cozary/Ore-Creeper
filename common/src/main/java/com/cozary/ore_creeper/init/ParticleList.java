package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.particles.ColoredParticleOptions;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ParticleList {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, OreCreeper.MOD_ID);

    public static final RegistryObject<ParticleType<ColoredParticleOptions>> COLORED_EXPLOSION = PARTICLES.register("colored_explosion", () -> new ParticleType<>(true) {
        @Override
        public MapCodec<ColoredParticleOptions> codec() {
            return ColoredParticleOptions.CODEC;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ColoredParticleOptions> streamCodec() {
            return ColoredParticleOptions.STREAM_CODEC;
        }
    });

    public static void loadClass() {
    }

}
