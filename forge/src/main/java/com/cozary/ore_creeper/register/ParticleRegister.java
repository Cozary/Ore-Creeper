package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.particles.ColoredExplosionParticle;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public class ParticleRegister {

    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleList.COLORED_EXPLOSION.get(), ColoredExplosionParticle.Factory::new);
    }
}
