package com.cozary.ore_creeper.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block) {
        super(block, new Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("ore_creeper", "ore_tnt")))
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(ChatFormatting.AQUA + "This Tnt transforms the" + ChatFormatting.GOLD + " Creepers" + ChatFormatting.AQUA + " it hits."));
        tooltipAdder.accept(Component.translatable(ChatFormatting.GRAY + "Its use is merely intended for Modpacks, not common use."));
    }

}
