package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

public class GetMaxVEProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		BlockState blocktype = Blocks.AIR.defaultBlockState();
		double MaxVoidExudate = 0;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == StellarExplorerModBlocks.VOID_TANK.get()) {
			return 40000;
		}
		return 400;
	}
}
