package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

@EventBusSubscriber(modid = OreCreeper.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void onGatherRegistries(GatherDataRegistryEntriesEvent event) {
        event.recipe(ModRecipeProvider::new)
                .lootTable(new LootTableProvider.SubProviderEntry(ModLootTableProvider::new, LootContextParamSets.ENTITY));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModBlockTagProvider::new);
        event.createProvider(ModWorldGenTagProvider::new);
    }
}
