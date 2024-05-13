package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.particles.BaseExplosionParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class ParticleRegister implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ParticleList.COAL_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.COPPER_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.COPPER_EXPLOSION_0.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.DIAMOND_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.EMERALD_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.GOLD_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.IRON_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.LAPIS_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.REDSTONE_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleList.WHITE_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
    }
}
