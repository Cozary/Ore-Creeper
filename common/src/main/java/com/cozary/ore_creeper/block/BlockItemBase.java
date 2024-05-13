package com.cozary.ore_creeper.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block) {
        super(block, new Properties());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable(ChatFormatting.AQUA + "This Tnt transforms the" + ChatFormatting.GOLD + " Creepers" + ChatFormatting.AQUA + " it hits."));
        tooltip.add(Component.translatable(ChatFormatting.GRAY + "Its use is merely intended for Modpacks, not common use."));
    }

}
