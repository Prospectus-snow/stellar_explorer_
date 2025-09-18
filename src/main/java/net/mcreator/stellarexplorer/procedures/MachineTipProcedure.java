package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class MachineTipProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		BlockState blocktype = Blocks.AIR.defaultBlockState();
		double MaxVoidExudate = 0;
		return Math.round(new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate")) + "/" + Math.round(GetMaxVEProcedure.execute(world, x, y, z));
	}
}
