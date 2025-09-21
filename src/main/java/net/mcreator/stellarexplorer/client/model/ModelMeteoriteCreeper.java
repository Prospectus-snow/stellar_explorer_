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
public class ModelMeteoriteCreeper<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("stellar_explorer", "model_meteorite_creeper"), "main");
	public final ModelPart all;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart head2;
	public final ModelPart right_leg_1_1;
	public final ModelPart right_leg_1_2;
	public final ModelPart right_leg_2_1;
	public final ModelPart right_leg_2_2;
	public final ModelPart left_leg_1_1;
	public final ModelPart left_leg_1_2;
	public final ModelPart left_leg_2_1;
	public final ModelPart left_leg_2_2;

	public ModelMeteoriteCreeper(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.head = this.body.getChild("head");
		this.head2 = this.head.getChild("head2");
		this.right_leg_1_1 = this.all.getChild("right_leg_1_1");
		this.right_leg_1_2 = this.right_leg_1_1.getChild("right_leg_1_2");
		this.right_leg_2_1 = this.all.getChild("right_leg_2_1");
		this.right_leg_2_2 = this.right_leg_2_1.getChild("right_leg_2_2");
		this.left_leg_1_1 = this.all.getChild("left_leg_1_1");
		this.left_leg_1_2 = this.left_leg_1_1.getChild("left_leg_1_2");
		this.left_leg_2_1 = this.all.getChild("left_leg_2_1");
		this.left_leg_2_2 = this.left_leg_2_1.getChild("left_leg_2_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 37).addBox(-3.0F, -17.0F, -1.0F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, -1.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, -17.0F, 1.0F));
		PartDefinition head2 = head.addOrReplaceChild("head2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 18).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_leg_1_1 = all.addOrReplaceChild("right_leg_1_1", CubeListBuilder.create().texOffs(16, 37).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, -6.0F, -2.0F, -0.4363F, 0.3491F, 2.0071F));
		PartDefinition right_leg_1_2 = right_leg_1_1.addOrReplaceChild("right_leg_1_2", CubeListBuilder.create().texOffs(16, 46).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.9199F));
		PartDefinition right_leg_2_1 = all.addOrReplaceChild("right_leg_2_1", CubeListBuilder.create().texOffs(16, 37).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, -6.0F, 2.0F, 0.4363F, -0.3491F, 2.0071F));
		PartDefinition right_leg_2_2 = right_leg_2_1.addOrReplaceChild("right_leg_2_2", CubeListBuilder.create().texOffs(16, 46).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.9199F));
		PartDefinition left_leg_1_1 = all.addOrReplaceChild("left_leg_1_1", CubeListBuilder.create().texOffs(16, 37).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.5F, -6.0F, -2.0F, -0.4363F, -0.3491F, -2.0071F));
		PartDefinition left_leg_1_2 = left_leg_1_1.addOrReplaceChild("left_leg_1_2", CubeListBuilder.create().texOffs(16, 46).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.0F, 1.9199F));
		PartDefinition left_leg_2_1 = all.addOrReplaceChild("left_leg_2_1", CubeListBuilder.create().texOffs(16, 37).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.5F, -6.0F, 2.0F, 0.4363F, 0.3491F, -2.0071F));
		PartDefinition left_leg_2_2 = left_leg_2_1.addOrReplaceChild("left_leg_2_2", CubeListBuilder.create().texOffs(16, 46).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.0F, 1.9199F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head2.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head2.xRot = headPitch / (180F / (float) Math.PI);
	}
}
