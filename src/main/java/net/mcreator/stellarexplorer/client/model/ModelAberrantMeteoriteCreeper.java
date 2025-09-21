package net.mcreator.stellarexplorer.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelAberrantMeteoriteCreeper<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("stellar_explorer", "model_aberrant_meteorite_creeper"), "main");
	public final ModelPart leg_all;
	public final ModelPart all;
	public final ModelPart body_all;
	public final ModelPart body;
	public final ModelPart body2;
	public final ModelPart head;
	public final ModelPart head2;
	public final ModelPart right_leg_1;
	public final ModelPart right_leg_1_1;
	public final ModelPart right_leg_1_2;
	public final ModelPart left_leg_1;
	public final ModelPart left_leg_1_1;
	public final ModelPart left_leg_1_2;
	public final ModelPart right_leg_2;
	public final ModelPart right_leg_2_1;
	public final ModelPart right_leg_2_2;
	public final ModelPart left_leg_2;
	public final ModelPart left_leg_2_1;
	public final ModelPart left_leg_2_2;

	public ModelAberrantMeteoriteCreeper(ModelPart root) {
		this.leg_all = root.getChild("leg_all");
		this.all = this.leg_all.getChild("all");
		this.body_all = this.all.getChild("body_all");
		this.body = this.body_all.getChild("body");
		this.body2 = this.body.getChild("body2");
		this.head = this.body2.getChild("head");
		this.head2 = this.head.getChild("head2");
		this.right_leg_1 = this.body_all.getChild("right_leg_1");
		this.right_leg_1_1 = this.right_leg_1.getChild("right_leg_1_1");
		this.right_leg_1_2 = this.right_leg_1_1.getChild("right_leg_1_2");
		this.left_leg_1 = this.body_all.getChild("left_leg_1");
		this.left_leg_1_1 = this.left_leg_1.getChild("left_leg_1_1");
		this.left_leg_1_2 = this.left_leg_1_1.getChild("left_leg_1_2");
		this.right_leg_2 = this.all.getChild("right_leg_2");
		this.right_leg_2_1 = this.right_leg_2.getChild("right_leg_2_1");
		this.right_leg_2_2 = this.right_leg_2_1.getChild("right_leg_2_2");
		this.left_leg_2 = this.all.getChild("left_leg_2");
		this.left_leg_2_1 = this.left_leg_2.getChild("left_leg_2_1");
		this.left_leg_2_2 = this.left_leg_2_1.getChild("left_leg_2_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition leg_all = partdefinition.addOrReplaceChild("leg_all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 15.0F));
		PartDefinition all = leg_all.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -15.0F));
		PartDefinition body_all = all.addOrReplaceChild("body_all", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));
		PartDefinition body = body_all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 71).addBox(-3.0F, -17.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 43).addBox(-3.0F, -22.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -17.0F, 0.0F, 1.0036F, 0.0F, 0.0F));
		PartDefinition head = body2.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, 0.0F));
		PartDefinition head2 = head.addOrReplaceChild("head2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -8.0F, 14.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(0, 22).addBox(-7.0F, 0.0F, -8.0F, 14.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		PartDefinition cube_r1 = head2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(43, 6).mirror().addBox(-1.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.0F, -2.5F, -1.0F, 0.0F, 0.0F, 0.7854F));
		PartDefinition cube_r2 = head2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(43, 6).mirror().addBox(-1.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(7.0F, -3.2F, -0.975F, 0.0F, 0.0F, 0.2618F));
		PartDefinition cube_r3 = head2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(7.0F, -4.5F, -1.0F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r4 = head2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(43, 6).addBox(-8.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -2.5F, -1.0F, 0.0F, 0.0F, -0.7854F));
		PartDefinition cube_r5 = head2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(43, 6).addBox(-8.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, -3.2F, -0.975F, 0.0F, 0.0F, -0.2618F));
		PartDefinition cube_r6 = head2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(42, 0).addBox(-9.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, -4.5F, -1.0F, 0.0F, 0.0F, 0.3054F));
		PartDefinition right_leg_1 = body_all.addOrReplaceChild("right_leg_1", CubeListBuilder.create(), PartPose.offset(-2.5F, -1.0F, -2.0F));
		PartDefinition right_leg_1_1 = right_leg_1.addOrReplaceChild("right_leg_1_1", CubeListBuilder.create().texOffs(52, 43).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.2618F, 1.9199F));
		PartDefinition right_leg_1_2 = right_leg_1_1.addOrReplaceChild("right_leg_1_2",
				CubeListBuilder.create().texOffs(24, 43).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(24, 74).addBox(-3.5F, 14.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, -1.3963F));
		PartDefinition cube_r7 = right_leg_1_2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(43, 6).addBox(-8.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 7.5F, 0.0F, 0.0F, 0.0F, -0.6981F));
		PartDefinition cube_r8 = right_leg_1_2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 0).addBox(-9.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 3.5F, 0.0F, 0.0F, 0.0F, -0.3054F));
		PartDefinition left_leg_1 = body_all.addOrReplaceChild("left_leg_1", CubeListBuilder.create(), PartPose.offset(2.5F, -1.0F, -2.0F));
		PartDefinition left_leg_1_1 = left_leg_1.addOrReplaceChild("left_leg_1_1", CubeListBuilder.create().texOffs(52, 43).mirror().addBox(-2.5F, -1.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.2618F, -1.9199F));
		PartDefinition left_leg_1_2 = left_leg_1_1.addOrReplaceChild("left_leg_1_2", CubeListBuilder.create().texOffs(24, 43).mirror().addBox(-3.5F, -1.0F, -3.5F, 7.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(24, 74).mirror()
				.addBox(-3.5F, 14.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, 1.3963F));
		PartDefinition cube_r9 = left_leg_1_2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(43, 6).mirror().addBox(-1.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 7.5F, 0.0F, 0.0F, 0.0F, 0.6981F));
		PartDefinition cube_r10 = left_leg_1_2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 3.5F, 0.0F, 0.0F, 0.0F, 0.3054F));
		PartDefinition right_leg_2 = all.addOrReplaceChild("right_leg_2", CubeListBuilder.create(), PartPose.offset(-2.5F, -15.0F, 3.0F));
		PartDefinition right_leg_2_1 = right_leg_2.addOrReplaceChild("right_leg_2_1", CubeListBuilder.create().texOffs(52, 43).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, -0.2618F, 1.9199F));
		PartDefinition right_leg_2_2 = right_leg_2_1.addOrReplaceChild("right_leg_2_2",
				CubeListBuilder.create().texOffs(24, 43).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(24, 74).addBox(-3.5F, 14.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, -1.3963F));
		PartDefinition cube_r11 = right_leg_2_2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(42, 0).addBox(-9.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 3.5F, 0.0F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r12 = right_leg_2_2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(43, 6).addBox(-8.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 7.5F, 0.0F, 0.0F, 0.0F, -0.6981F));
		PartDefinition left_leg_2 = all.addOrReplaceChild("left_leg_2", CubeListBuilder.create(), PartPose.offset(2.5F, -15.0F, 3.0F));
		PartDefinition left_leg_2_1 = left_leg_2.addOrReplaceChild("left_leg_2_1", CubeListBuilder.create().texOffs(52, 43).mirror().addBox(-2.5F, -1.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.2618F, -1.9199F));
		PartDefinition left_leg_2_2 = left_leg_2_1.addOrReplaceChild("left_leg_2_2", CubeListBuilder.create().texOffs(24, 43).mirror().addBox(-3.5F, -1.0F, -3.5F, 7.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(24, 74).mirror()
				.addBox(-3.5F, 14.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, 1.3963F));
		PartDefinition cube_r13 = left_leg_2_2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, -4.5F, 0.0F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 3.5F, 0.0F, 0.0F, 0.0F, 0.3054F));
		PartDefinition cube_r14 = left_leg_2_2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(43, 6).mirror().addBox(-1.0F, -2.5F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 7.5F, 0.0F, 0.0F, 0.0F, 0.6981F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg_all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head2.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head2.xRot = headPitch / (180F / (float) Math.PI);
	}
}
