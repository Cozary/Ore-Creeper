package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

public class ParticleList {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, OreCreeper.MOD_ID);

    public static final RegistryObject<SimpleParticleType> COAL_EXPLOSION = PARTICLES.register("coal_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> COPPER_EXPLOSION = PARTICLES.register("copper_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> COPPER_EXPLOSION_0 = PARTICLES.register("copper_explosion_0", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> DIAMOND_EXPLOSION = PARTICLES.register("diamond_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> EMERALD_EXPLOSION = PARTICLES.register("emerald_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> GOLD_EXPLOSION = PARTICLES.register("gold_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> IRON_EXPLOSION = PARTICLES.register("iron_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> LAPIS_EXPLOSION = PARTICLES.register("lapis_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> REDSTONE_EXPLOSION = PARTICLES.register("redstone_explosion", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> WHITE_EXPLOSION = PARTICLES.register("white_explosion", () -> new SimpleParticleType(true) {
    });

    public static void loadClass() {
    }

}
