package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class VoidExudateBarOverlayProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double MaxVoidExudate = 0;
		MaxVoidExudate = GetMaxVEProcedure.execute(world, x, y, z);
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate") > 0) {
			if (64 * ((new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate")) / MaxVoidExudate) < 1) {
				return 1;
			} else {
				return Math.round(64 * ((new Object() {
					public double getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getDouble(tag);
						return -1;
					}
				}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate")) / MaxVoidExudate));
			}
		}
		return 0;
	}
}
