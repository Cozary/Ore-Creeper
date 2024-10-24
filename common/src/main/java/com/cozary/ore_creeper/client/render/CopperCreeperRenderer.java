package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.client.model.OreCreeperModel;
import com.cozary.ore_creeper.entities.CopperCreeperEntity;
import com.cozary.ore_creeper.util.ClientEventBusSubscriber;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class CopperCreeperRenderer extends MobRenderer<CopperCreeperEntity, OreCreeperModel<CopperCreeperEntity>> {

    protected static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "textures/entity/copper_creeper.png");
    ResourceLocation entityIconLoc = ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, OreCreeper.MOD_ID.replace("/entity/", "/entity_icon/"));

    public CopperCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new OreCreeperModel<>(context.bakeLayer(ClientEventBusSubscriber.COPPER_CREEPER)), 0.5F);
    }

    protected void scale(CopperCreeperEntity p_114046_, PoseStack p_114047_, float p_114048_) {
        float f = p_114046_.getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(CopperCreeperEntity p_114043_, float p_114044_) {
        float f = p_114043_.getSwelling(p_114044_);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull CopperCreeperEntity p_114041_) {
        return TEXTURE;
    }

    public @NotNull ResourceLocation getEntityIconLoc(@NotNull CopperCreeperEntity p_114041_) {
        return entityIconLoc;
    }
}
