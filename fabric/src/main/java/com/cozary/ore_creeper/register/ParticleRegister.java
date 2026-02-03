package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.particles.ColoredExplosionParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class ParticleRegister implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(ParticleList.COLORED_EXPLOSION.get(), ColoredExplosionParticle.Factory::new);

    }
}
