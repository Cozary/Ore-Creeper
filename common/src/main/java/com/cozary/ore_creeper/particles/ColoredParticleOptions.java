package com.cozary.ore_creeper.particles;

import com.cozary.ore_creeper.init.ParticleList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.joml.Vector3f;

public record ColoredParticleOptions(Vector3f color) implements ParticleOptions {

    public static final MapCodec<ColoredParticleOptions> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("r").forGetter(o -> o.color.x),
            Codec.FLOAT.fieldOf("g").forGetter(o -> o.color.y),
            Codec.FLOAT.fieldOf("b").forGetter(o -> o.color.z)
    ).apply(instance, (r, g, b) -> new ColoredParticleOptions(new Vector3f(r, g, b))));

    public static final StreamCodec<RegistryFriendlyByteBuf, ColoredParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, o -> o.color.x,
            ByteBufCodecs.FLOAT, o -> o.color.y,
            ByteBufCodecs.FLOAT, o -> o.color.z,
            (r, g, b) -> new ColoredParticleOptions(new Vector3f(r, g, b))
    );

    @Override
    public ParticleType<?> getType() {
        return ParticleList.COLORED_EXPLOSION.get();
    }
}
