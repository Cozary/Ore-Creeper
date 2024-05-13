package com.cozary.ore_creeper.init;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OreCreeper.MOD_ID);

    public static RegistryObject<CreativeModeTab> ORE_CREEPER_TAB = CREATIVE_MODE_TAB.register("ore_creeper", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.ORE_TNT.get()))
            .title(Component.translatable("itemGroup.ore_creeper"))
            .displayItems((parameters, output) -> ModSpawnEggs.SPAWNEGGS_TAB.forEach((item) -> output.accept(item.get())))
            .build());

}


