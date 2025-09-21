package net.mcreator.stellarexplorer.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.BlockState;

public class BlockBreakParticleSpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z,
							   double XZdistance, double Ydistance,
							   double density, double speed) {

		if (!(world instanceof ServerLevel level))
			return;

		// 计算下方0.1格的位置
		double belowY = y - 0.2;
		BlockPos belowPos = BlockPos.containing(x, belowY, z);

		// 获取该位置的方块状态
		BlockState blockState = level.getBlockState(belowPos);

		// 如果方块是空气，使用默认的石头粒子


		// 创建方块粒子选项
		BlockParticleOption particleOption = new BlockParticleOption(
				ParticleTypes.BLOCK,
				blockState
		);

		// 生成粒子
		level.sendParticles(
				particleOption,      // 粒子类型 + 方块状态
				x, y, z,             // 生成位置
				(int) density,       // 粒子数量
				XZdistance,          // X方向随机偏移范围
				Ydistance,           // Y方向随机偏移范围
				XZdistance,          // Z方向随机偏移范围
				speed                // 粒子初始速度
		);
	}
}
