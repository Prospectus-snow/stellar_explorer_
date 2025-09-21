
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

import net.mcreator.stellarexplorer.procedures.MeteoriteCreeperSizeProcedure;
import net.mcreator.stellarexplorer.procedures.MeteoriteCreeperAngryProcedure;
import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;
import net.mcreator.stellarexplorer.client.model.animations.MeteoriteCreeperAnimation;
import net.mcreator.stellarexplorer.client.model.ModelMeteoriteCreeper;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class MeteoriteCreeperRenderer extends MobRenderer<MeteoriteCreeperEntity, ModelMeteoriteCreeper<MeteoriteCreeperEntity>> {
	public MeteoriteCreeperRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelMeteoriteCreeper.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<MeteoriteCreeperEntity, ModelMeteoriteCreeper<MeteoriteCreeperEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("stellar_explorer:textures/entities/meteorite_creeper_angry.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, MeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (MeteoriteCreeperAngryProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
				}
			}
		});
	}

	@Override
	protected void scale(MeteoriteCreeperEntity entity, PoseStack poseStack, float f) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		float scale = (float) MeteoriteCreeperSizeProcedure.execute(entity);
		poseStack.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(MeteoriteCreeperEntity entity) {
		return new ResourceLocation("stellar_explorer:textures/entities/meteorite_creeper.png");
	}

	private static final class AnimatedModel extends ModelMeteoriteCreeper<MeteoriteCreeperEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<MeteoriteCreeperEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(MeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(MeteoriteCreeperAnimation.walk, limbSwing, limbSwingAmount, 2.5f, 1f);
				this.animate(entity.animationState1, MeteoriteCreeperAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(MeteoriteCreeperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
