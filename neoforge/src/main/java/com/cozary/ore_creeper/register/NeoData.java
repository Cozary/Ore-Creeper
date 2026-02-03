package com.cozary.ore_creeper.register;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperManager;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;

@EventBusSubscriber(modid = OreCreeper.MOD_ID)
public final class NeoData {
    @SubscribeEvent
    public static void onAddReloadListeners(AddServerReloadListenersEvent event) {
        event.addListener(Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_creeper_types"), new BaseOreCreeperManager());
    }
}