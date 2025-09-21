
package net.mcreator.stellarexplorer.client.renderer;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperSizeProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationWalkProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAngryProcedure;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;
import net.mcreator.stellarexplorer.client.model.animations.AberrantMeteoriteCreeperAnimation;
import net.mcreator.stellarexplorer.client.model.ModelAberrantMeteoriteCreeper;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class AberrantMeteoriteCreeperRenderer extends MobRenderer<AberrantMeteoriteCreeperEntity, ModelAberrantMeteoriteCreeper<AberrantMeteoriteCreeperEntity>> {
	public AberrantMeteoriteCreeperRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelAberrantMeteoriteCreeper.LAYER_LOCATION)), 0.8f);
		this.addLayer(new RenderLayer<AberrantMeteoriteCreeperEntity, ModelAberrantMeteoriteCreeper<AberrantMeteoriteCreeperEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("stellar_explorer:textures/entities/aberrant_meteorite_creeper_angry.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, AberrantMeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (AberrantMeteoriteCreeperAngryProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}

				entity.updateBodyRotationForRender(partialTicks);
			}
		});
	}

	@Override
	protected void scale(AberrantMeteoriteCreeperEntity entity, PoseStack poseStack, float f) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		float scale = (float) AberrantMeteoriteCreeperSizeProcedure.execute(entity);
		poseStack.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(AberrantMeteoriteCreeperEntity entity) {
		return new ResourceLocation("stellar_explorer:textures/entities/aberrant_meteorite_creeper.png");
	}

	private static final class AnimatedModel extends ModelAberrantMeteoriteCreeper<AberrantMeteoriteCreeperEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<AberrantMeteoriteCreeperEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(AberrantMeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, AberrantMeteoriteCreeperAnimation.idle, ageInTicks, 1f);
				if (AberrantMeteoriteCreeperAnimationWalkProcedure.execute(entity))
					this.animateWalk(AberrantMeteoriteCreeperAnimation.walk, limbSwing, limbSwingAmount, 1.2f, 1f);
				this.animate(entity.animationState2, AberrantMeteoriteCreeperAnimation.attack1_smash, ageInTicks, 1f);
				this.animate(entity.animationState3, AberrantMeteoriteCreeperAnimation.attack2_head, ageInTicks, 1f);
				this.animate(entity.animationState4, AberrantMeteoriteCreeperAnimation.attack3_super_smash, ageInTicks, 1f);
				this.animate(entity.animationState5, AberrantMeteoriteCreeperAnimation.attack4_shoot, ageInTicks, 1f);
				this.animate(entity.animationState6, AberrantMeteoriteCreeperAnimation.attack5_jumpstart, ageInTicks, 1f);
				this.animate(entity.animationState7, AberrantMeteoriteCreeperAnimation.attack6_jumpend, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(AberrantMeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
