package com.cozary.ore_creeper.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;


public class OreCreeperModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public OreCreeperModel(ModelPart p_170524_) {
        this.root = p_170524_;
        this.head = p_170524_.getChild("head");
        this.leftHindLeg = p_170524_.getChild("right_hind_leg");
        this.rightHindLeg = p_170524_.getChild("left_hind_leg");
        this.leftFrontLeg = p_170524_.getChild("right_front_leg");
        this.rightFrontLeg = p_170524_.getChild("left_front_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-2.0F, 18.0F, 4.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(2.0F, 18.0F, 4.0F));
        partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-2.0F, 18.0F, -4.0F));
        partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(2.0F, 18.0F, -4.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public @NotNull ModelPart root() {
        return this.root;
    }

    public void setupAnim(@NotNull T p_102463_, float p_102464_, float p_102465_, float p_102466_, float p_102467_, float p_102468_) {
        this.head.yRot = p_102467_ * ((float) Math.PI / 180F);
        this.head.xRot = p_102468_ * ((float) Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(p_102464_ * 0.6662F) * 1.4F * p_102465_;
        this.leftHindLeg.xRot = Mth.cos(p_102464_ * 0.6662F + (float) Math.PI) * 1.4F * p_102465_;
        this.rightFrontLeg.xRot = Mth.cos(p_102464_ * 0.6662F + (float) Math.PI) * 1.4F * p_102465_;
        this.leftFrontLeg.xRot = Mth.cos(p_102464_ * 0.6662F) * 1.4F * p_102465_;
    }

}
