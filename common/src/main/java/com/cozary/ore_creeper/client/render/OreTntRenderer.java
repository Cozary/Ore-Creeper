package com.cozary.ore_creeper.client.render;


import com.cozary.ore_creeper.entities.OrePrimedTnt;
import com.cozary.ore_creeper.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class OreTntRenderer extends EntityRenderer<OrePrimedTnt, TntRenderState> {

    public OreTntRenderer(EntityRendererProvider.Context p_174426_) {
        super(p_174426_);
        this.shadowRadius = 0.5F;
    }

    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void submit(TntRenderState tntRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
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

        TntMinecartRenderer.submitWhiteSolidBlock(ModBlocks.ORE_TNT.get().defaultBlockState(), poseStack, submitNodeCollector, tntRenderState.lightCoords, (int) f / 5 % 2 == 0, tntRenderState.outlineColor);

        poseStack.popPose();
        super.submit(tntRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public @NotNull Identifier getTextureLocation(@NotNull OrePrimedTnt p_116175_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    public void extractRenderState(OrePrimedTnt p_361380_, TntRenderState p_364625_, float p_360472_) {
        super.extractRenderState(p_361380_, p_364625_, p_360472_);
        p_364625_.fuseRemainingInTicks = (float) p_361380_.getFuse() - p_360472_ + 1.0F;
        p_364625_.blockState = p_361380_.getBlockState();
    }
}