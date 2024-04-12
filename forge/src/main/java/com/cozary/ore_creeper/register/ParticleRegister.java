package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.particles.BaseExplosionParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OreCreeper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegister {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleList.COAL_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.COPPER_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.COPPER_EXPLOSION_0.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.DIAMOND_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.EMERALD_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.GOLD_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.IRON_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.LAPIS_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.REDSTONE_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
        event.registerSpriteSet(ParticleList.WHITE_EXPLOSION.get(), BaseExplosionParticle.Factory::new);
    }
}
