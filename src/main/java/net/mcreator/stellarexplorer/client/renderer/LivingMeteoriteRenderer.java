
package net.mcreator.stellarexplorer.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.stellarexplorer.entity.LivingMeteoriteEntity;
import net.mcreator.stellarexplorer.client.model.animations.LivingMeteoriteAnimation;
import net.mcreator.stellarexplorer.client.model.ModelLivingMeteorite;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class LivingMeteoriteRenderer extends MobRenderer<LivingMeteoriteEntity, ModelLivingMeteorite<LivingMeteoriteEntity>> {
	public LivingMeteoriteRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelLivingMeteorite.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<LivingMeteoriteEntity, ModelLivingMeteorite<LivingMeteoriteEntity>>(this) {
			private final ResourceLocation LAYER_TEXTURE =
					new ResourceLocation("stellar_explorer:textures/entities/living_meteorite_glow.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light,
							   LivingMeteoriteEntity entity, float limbSwing, float limbSwingAmount,
							   float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				int fullBright = 0xF000F0;
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucentEmissive(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, fullBright,
						LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(LivingMeteoriteEntity entity) {
		return new ResourceLocation("stellar_explorer:textures/entities/living_meteorite.png");
	}

	private static final class AnimatedModel extends ModelLivingMeteorite<LivingMeteoriteEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<LivingMeteoriteEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(LivingMeteoriteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, LivingMeteoriteAnimation.idle, ageInTicks, 1f);
				this.animate(entity.animationState1, LivingMeteoriteAnimation.attack1_shoot, ageInTicks, 1f);
				this.animate(entity.animationState2, LivingMeteoriteAnimation.attack2_rush, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(LivingMeteoriteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
