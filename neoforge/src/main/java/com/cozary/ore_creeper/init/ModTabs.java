package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OreCreeper.MOD_ID);

    public static final Supplier<CreativeModeTab> ORE_CREEPER_TAB = CREATIVE_MODE_TAB.register("ore_creeper", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ore_creeper"))
            .icon(() -> new ItemStack(ModBlocks.ORE_TNT.get()))
            .displayItems((parameters, output) -> ModSpawnEggs.SPAWNEGGS_TAB.forEach((item) -> output.accept(item.get())))
            .build());

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}


