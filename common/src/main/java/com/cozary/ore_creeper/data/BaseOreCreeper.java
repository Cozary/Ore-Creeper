package com.cozary.ore_creeper.data;

import com.cozary.ore_creeper.util.IOreExplosionConfig;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public record BaseOreCreeper(
        Identifier oreBlockId,
        Optional<Identifier> rawBlockId,
        Optional<Identifier> deepslateOreBlockId,
        float radius,
        Identifier texture,
        Optional<Identifier> itemTexture,
        int particleColor,
        Optional<Integer> secondaryParticleColor,
        Optional<TagKey<Biome>> spawnInBiomes,
        Optional<TagKey<Biome>> removeFromBiomes,
        boolean isNether,
        int maxSpawnYLevel,
        int spawnWeight,
        int minGroupSize,
        int maxGroupSize
) implements IOreExplosionConfig {

    public static final Codec<Integer> COLOR_CODEC = Codec.either(Codec.INT, Codec.STRING).xmap(
            either -> either.map(
                    integer -> integer,
                    string -> {
                        String hex = string.startsWith("#") ? string.substring(1) : string;
                        return Integer.parseInt(hex, 16);
                    }
            ),
            Either::left
    );

    public static final Codec<BaseOreCreeper> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("ore_block").forGetter(BaseOreCreeper::oreBlockId),
            Identifier.CODEC.optionalFieldOf("raw_block").forGetter(BaseOreCreeper::rawBlockId),
            Identifier.CODEC.optionalFieldOf("deepslate_ore_block").forGetter(BaseOreCreeper::deepslateOreBlockId),
            Codec.FLOAT.fieldOf("radius").orElse(3.0f).forGetter(BaseOreCreeper::radius),
            Identifier.CODEC.fieldOf("texture").forGetter(BaseOreCreeper::texture),
            Identifier.CODEC.optionalFieldOf("item_texture").forGetter(BaseOreCreeper::itemTexture),
            COLOR_CODEC.fieldOf("particle_color").orElse(0xFFFFFF).forGetter(BaseOreCreeper::particleColor),
            COLOR_CODEC.optionalFieldOf("secondary_particle_color").forGetter(BaseOreCreeper::secondaryParticleColor),
            TagKey.hashedCodec(Registries.BIOME).optionalFieldOf("spawn_in_biomes").forGetter(BaseOreCreeper::spawnInBiomes),
            TagKey.hashedCodec(Registries.BIOME).optionalFieldOf("remove_from_biomes").forGetter(BaseOreCreeper::removeFromBiomes),
            Codec.BOOL.fieldOf("is_nether").orElse(false).forGetter(BaseOreCreeper::isNether),
            Codec.INT.fieldOf("max_spawn_y_level").orElse(320).forGetter(BaseOreCreeper::maxSpawnYLevel),
            Codec.INT.fieldOf("spawn_weight").orElse(10).forGetter(BaseOreCreeper::spawnWeight),
            Codec.INT.fieldOf("min_group_size").orElse(1).forGetter(BaseOreCreeper::minGroupSize),
            Codec.INT.fieldOf("max_group_size").orElse(3).forGetter(BaseOreCreeper::maxGroupSize)
    ).apply(instance, BaseOreCreeper::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BaseOreCreeper> STREAM_CODEC = StreamCodec.of(
            (buf, val) -> {
                Identifier.STREAM_CODEC.encode(buf, val.oreBlockId());
                ByteBufCodecs.optional(Identifier.STREAM_CODEC).encode(buf, val.rawBlockId());
                ByteBufCodecs.optional(Identifier.STREAM_CODEC).encode(buf, val.deepslateOreBlockId());
                ByteBufCodecs.FLOAT.encode(buf, val.radius());
                Identifier.STREAM_CODEC.encode(buf, val.texture());
                ByteBufCodecs.optional(Identifier.STREAM_CODEC).encode(buf, val.itemTexture());
                ByteBufCodecs.INT.encode(buf, val.particleColor());
                ByteBufCodecs.optional(ByteBufCodecs.INT).encode(buf, val.secondaryParticleColor());
                ByteBufCodecs.optional(Identifier.STREAM_CODEC).encode(buf, val.spawnInBiomes().map(TagKey::location));
                ByteBufCodecs.optional(Identifier.STREAM_CODEC).encode(buf, val.removeFromBiomes().map(TagKey::location));
                ByteBufCodecs.BOOL.encode(buf, val.isNether());
                ByteBufCodecs.INT.encode(buf, val.maxSpawnYLevel());
                ByteBufCodecs.INT.encode(buf, val.spawnWeight());
                ByteBufCodecs.INT.encode(buf, val.minGroupSize());
                ByteBufCodecs.INT.encode(buf, val.maxGroupSize());
            },
            buf -> new BaseOreCreeper(
                    Identifier.STREAM_CODEC.decode(buf),
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC).decode(buf),
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC).decode(buf),
                    ByteBufCodecs.FLOAT.decode(buf),
                    Identifier.STREAM_CODEC.decode(buf),
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC).decode(buf),
                    ByteBufCodecs.INT.decode(buf),
                    ByteBufCodecs.optional(ByteBufCodecs.INT).decode(buf),
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC).decode(buf).map(id -> TagKey.create(Registries.BIOME, id)),
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC).decode(buf).map(id -> TagKey.create(Registries.BIOME, id)),
                    ByteBufCodecs.BOOL.decode(buf),
                    ByteBufCodecs.INT.decode(buf),
                    ByteBufCodecs.INT.decode(buf),
                    ByteBufCodecs.INT.decode(buf),
                    ByteBufCodecs.INT.decode(buf)
            )
    );

    @Override
    public Block getOreBlock() {
        return BuiltInRegistries.BLOCK.getOptional(oreBlockId).map(holder -> holder.defaultBlockState().getBlock()).orElse(Blocks.AIR);
    }

    @Override
    public Block getRawBlock() {
        return rawBlockId.flatMap(id -> BuiltInRegistries.BLOCK.getOptional(id)).map(holder -> holder.defaultBlockState().getBlock()).orElse(null);
    }

    @Override
    public Block getDeepslateOreBlock() {
        return deepslateOreBlockId.flatMap(id -> BuiltInRegistries.BLOCK.getOptional(id)).map(holder -> holder.defaultBlockState().getBlock()).orElse(null);
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public Identifier getTextureId() {
        return texture;
    }

    @Override
    public int getParticleColor() {
        return particleColor;
    }

    @Override
    public Optional<Integer> getSecondaryParticleColor() {
        return secondaryParticleColor;
    }

    @Override
    public boolean isNether() {
        return isNether;
    }

    @Override
    public int getMaxSpawnYLevel() {
        return maxSpawnYLevel;
    }
}