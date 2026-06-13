package com.cozary.ore_creeper.client.render;


import com.cozary.ore_creeper.entities.OrePrimedTnt;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class OreTntRenderer extends EntityRenderer<OrePrimedTnt, TntRenderState> {
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockModelResolver;

    public OreTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockModelResolver = context.getBlockModelResolver();
    }

    public @NonNull TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void submit(TntRenderState tntRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
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

        if (!tntRenderState.blockState.isEmpty()) {
            TntMinecartRenderer.submitWhiteSolidBlock(tntRenderState.blockState, poseStack, submitNodeCollector, tntRenderState.lightCoords, (int) f / 5 % 2 == 0, tntRenderState.outlineColor);
        }

        poseStack.popPose();
        super.submit(tntRenderState, poseStack, submitNodeCollector, camera);
    }

    public void extractRenderState(OrePrimedTnt entity, TntRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.fuseRemainingInTicks = (float) entity.getFuse() - partialTicks + 1.0F;
        this.blockModelResolver.update(state.blockState, entity.getBlockState(), BLOCK_DISPLAY_CONTEXT);
    }
}