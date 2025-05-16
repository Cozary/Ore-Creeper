package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.entities.AncientDebrisCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class AncientDebrisCreeperRenderer extends MobRenderer<AncientDebrisCreeperEntity, CreeperRenderState, OreCreeperModel> {

    protected static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "textures/entity/ancient_debris_creeper.png");
    ResourceLocation entityIconLoc = ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, OreCreeper.MOD_ID.replace("/entity/", "/entity_icon/"));

    public AncientDebrisCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new OreCreeperModel(context.bakeLayer(ClientEventBusSubscriber.ANCIENT_DEBRIS_CREEPER)), 0.5F);
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    protected void scale(CreeperRenderState p_114046_, PoseStack p_114047_, float p_114048_) {
        float f = p_114046_.swelling;
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(CreeperRenderState p_114043_, float p_114044_) {
        float f = p_114043_.swelling;
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(CreeperRenderState creeperRenderState) {
        return TEXTURE;
    }

    public @NotNull ResourceLocation getEntityIconLoc(@NotNull AncientDebrisCreeperEntity p_114041_) {
        return entityIconLoc;
    }

    public void extractRenderState(AncientDebrisCreeperEntity p_364394_, CreeperRenderState p_361451_, float p_364659_) {
        super.extractRenderState(p_364394_, p_361451_, p_364659_);
        p_361451_.swelling = p_364394_.getSwelling(p_364659_);
        p_361451_.isPowered = p_364394_.isPowered();
    }

}
