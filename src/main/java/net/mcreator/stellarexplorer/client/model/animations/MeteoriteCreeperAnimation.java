package net.mcreator.stellarexplorer.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 4.12.6 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class MeteoriteCreeperAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(0.6F).looping()
			.addAnimation("right_leg_1_1",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(21.9386F, -29.0273F, 11.7581F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.15F, KeyframeAnimations.degreeVec(-0.018F, 8.9657F, 20.5177F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(-26.1516F, 14.9921F, -18.1538F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(21.9386F, -29.0273F, 11.7581F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("right_leg_1_2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(5.25F, -2.9932F, 12.9541F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("right_leg_2_1",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-28.3477F, 26.0734F, 8.8274F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(34.9401F, -6.6477F, -8.897F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.45F, KeyframeAnimations.degreeVec(-1.546F, 5.8852F, 27.5251F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(-28.3477F, 26.0734F, 8.8274F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("left_leg_1_1",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-26.1516F, -14.9921F, 18.1538F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(21.9386F, 29.0273F, -11.7581F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.45F, KeyframeAnimations.degreeVec(-0.018F, -8.9657F, -20.5177F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(-26.1516F, -14.9921F, 18.1538F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("left_leg_1_2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.25F, -2.9932F, 12.9541F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(5.25F, -2.9932F, 12.9541F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("left_leg_2_1",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(34.9401F, 6.6477F, 8.897F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.15F, KeyframeAnimations.degreeVec(-1.546F, -5.8852F, -27.5251F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3F, KeyframeAnimations.degreeVec(-28.3477F, -26.0734F, -8.8274F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(34.9401F, 6.6477F, 8.897F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.15F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.45F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition attack = AnimationDefinition.Builder.withLength(1.25F)
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75F, KeyframeAnimations.degreeVec(77.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
}
