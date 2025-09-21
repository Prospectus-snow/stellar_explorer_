package net.mcreator.stellarexplorer.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class BlockBreakParticleSpawn2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z,
							   double distance, double getangle,
							   double speedXZ, double speedY) {
		// 删除全局方块状态获取
		generateParticleRing(world, x, y, z, distance, getangle, 0, speedXZ, speedY);
		generateParticleRing(world, x, y, z, distance * 0.7, getangle, getangle / 2, speedXZ, speedY);
		generateParticleRing(world, x, y, z, distance * 0.4, getangle, 0, speedXZ, speedY);
	}

	private static void generateParticleRing(LevelAccessor world, double x, double y, double z,
											 double distance, double getangle, double startAngle,
											 double speedXZ, double speedY) {
		double angle = startAngle;
		for (int i = 0; i < (int) getangle; i++) {
			angle += 360 / getangle;
			spawnParticle(world, x, y, z, distance, angle, speedXZ, speedY);
		}
	}

	private static void spawnParticle(LevelAccessor world, double originX, double originY, double originZ,
									  double distance, double angle,
									  double speedXZ, double speedY) {
		// 只在客户端生成粒子
		if (world.isClientSide()) {
			// 计算粒子生成坐标
			double radian = Math.toRadians(angle);
			double offsetX = distance * Math.sin(radian);
			double offsetZ = distance * Math.cos(radian);

			double particleX = originX + offsetX;
			double particleY = originY;
			double particleZ = originZ + offsetZ;

			// 获取该粒子位置下方0.1格的方块
			BlockPos particlePos = BlockPos.containing(particleX, particleY - 0.1, particleZ);
			BlockState blockState = world.getBlockState(particlePos);

			// 如果下方是空气，使用原点位置方块
			if (blockState.isAir()) {
				particlePos = BlockPos.containing(originX, originY - 0.1, originZ);
				blockState = world.getBlockState(particlePos);
			}

			// 创建粒子选项
			ParticleOptions particleType = new BlockParticleOption(ParticleTypes.BLOCK, blockState);

			// 随机速度计算
			RandomSource rand = RandomSource.create();
			double motionX = Mth.nextDouble(rand, speedXZ * 0.2, speedXZ * 1.15) * Math.sin(radian);
			double motionY = Mth.nextDouble(rand, speedY * 0.85, speedY * 1.15);
			double motionZ = Mth.nextDouble(rand, speedXZ * 0.2, speedXZ * 1.15) * Math.cos(radian);

			// 生成粒子（添加垂直偏移）
			((Level) world).addParticle(
					particleType,
					particleX,
					particleY + 0.1,  // 防止嵌入地面
					particleZ,
					motionX,
					motionY,
					motionZ
			);
		}
	}
}
