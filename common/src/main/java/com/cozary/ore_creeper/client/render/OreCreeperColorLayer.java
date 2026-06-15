package com.cozary.ore_creeper.client.render;

import com.cozary.ore_creeper.OreCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class OreCreeperColorLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
    private static final Identifier ORE_TEXTURE = Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "textures/entity/ore_creeper_base_ore.png");

    public OreCreeperColorLayer(RenderLayerParent<CreeperRenderState, CreeperModel> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, CreeperRenderState state, float yaw, float pitch) {
        if (state instanceof BaseOreCreeperRenderer.BaseCreeperRenderState baseState && baseState.oreColor.isPresent()) {
            int color = baseState.oreColor.get() | 0xFF000000;
            renderColoredCutoutModel(this.getParentModel(), ORE_TEXTURE, poseStack, submitNodeCollector, packedLight, state, color, 0);
        }
    }
}
