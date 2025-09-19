
package net.mcreator.stellarexplorer.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.stellarexplorer.entity.MeteorEntity;
import net.mcreator.stellarexplorer.client.model.animations.MeteorAnimation;
import net.mcreator.stellarexplorer.client.model.ModelMeteor;

import com.mojang.blaze3d.vertex.PoseStack;

public class MeteorRenderer extends MobRenderer<MeteorEntity, ModelMeteor<MeteorEntity>> {
	public MeteorRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelMeteor.LAYER_LOCATION)), 1.5f);
	}
	@Override
	public boolean shouldRender(MeteorEntity entity, Frustum frustum, double camX, double camY, double camZ) {
		return true; // 永远渲染
	}



	@Override
	public void render(MeteorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		// 强制最高亮度
		int fullBright = 0xF000F0;
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, fullBright);
	}

	@Override
	protected void scale(MeteorEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(3f, 3f, 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(MeteorEntity entity) {
		return new ResourceLocation("stellar_explorer:textures/entities/meteor.png");
	}



	private static final class AnimatedModel extends ModelMeteor<MeteorEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<MeteorEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(MeteorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, MeteorAnimation.rolling, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}


		@Override
		public void setupAnim(MeteorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
