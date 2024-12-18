package com.cozary.ore_creeper.client.render;


import com.cozary.ore_creeper.entities.OrePrimedTnt;
import com.cozary.ore_creeper.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.NotNull;

public class OreTntRenderer extends EntityRenderer<OrePrimedTnt, TntRenderState> {
    private final BlockRenderDispatcher blockRenderer;

    public OreTntRenderer(EntityRendererProvider.Context p_174426_) {
        super(p_174426_);
        this.shadowRadius = 0.5F;
        this.blockRenderer = p_174426_.getBlockRenderDispatcher();
    }

    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void render(TntRenderState tntRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5F, 0.0F);
        float f = tntRenderState.fuseRemainingInTicks;
        if (tntRenderState.fuseRemainingInTicks < 10.0F) {
            float f1 = 1.0F - tntRenderState.fuseRemainingInTicks / 10.0F;
            f1 = Mth.clamp(f1, 0.0F, 1.0F);
            f1 *= f1;
            f1 *= f1;
            float f2 = 1.0F + f1 * 0.3F;
            poseStack.scale(f2, f2, f2);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));

        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, ModBlocks.ORE_TNT.get().defaultBlockState(), poseStack, multiBufferSource, packedLight, (int) f / 5 % 2 == 0);

        poseStack.popPose();
        super.render(tntRenderState, poseStack, multiBufferSource, packedLight);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull OrePrimedTnt p_116175_) {
        return InventoryMenu.BLOCK_ATLAS;
    }

    public void extractRenderState(OrePrimedTnt p_361380_, TntRenderState p_364625_, float p_360472_) {
        super.extractRenderState(p_361380_, p_364625_, p_360472_);
        p_364625_.fuseRemainingInTicks = (float) p_361380_.getFuse() - p_360472_ + 1.0F;
        p_364625_.blockState = p_361380_.getBlockState();
    }
}