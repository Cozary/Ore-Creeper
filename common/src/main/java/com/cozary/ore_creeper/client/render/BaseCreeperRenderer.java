package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;

public class BaseCreeperRenderer<T extends Creeper> extends MobRenderer<T, CreeperRenderState, OreCreeperModel> {

    private final Identifier texture;

    public BaseCreeperRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, String textureName) {
        super(context, new OreCreeperModel(context.bakeLayer(layer)), 0.5F);
        this.texture = Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "textures/entity/" + textureName + ".png");
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    protected void scale(CreeperRenderState state, PoseStack poseStack) {
        float f = state.swelling;
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        poseStack.scale(f2, f3, f2);
    }

    @Override
    protected float getWhiteOverlayProgress(CreeperRenderState state) {
        float f = state.swelling;
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public Identifier getTextureLocation(CreeperRenderState state) {
        return texture;
    }

    @Override
    public void extractRenderState(T entity, CreeperRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.swelling = entity.getSwelling(partialTick);
        state.isPowered = entity.isPowered();
    }
}
