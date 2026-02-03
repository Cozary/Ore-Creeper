package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.particles.ColoredExplosionParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = OreCreeper.MOD_ID, value = Dist.CLIENT)
public class ParticleRegister {

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleList.COLORED_EXPLOSION.get(), ColoredExplosionParticle.Factory::new);
    }
}
