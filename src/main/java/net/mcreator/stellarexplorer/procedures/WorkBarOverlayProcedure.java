package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

public class WorkBarOverlayProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double MaxVoidExudate = 0;
		double MaxWorking = 0;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == StellarExplorerModBlocks.REDSTONE_EXTRACTOR.get()) {
			MaxWorking = 1500;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == StellarExplorerModBlocks.METEOR_ATTRACTOR.get()) {
			MaxWorking = 400;
		}
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Working") > 0) {
			return Math.round(64 * ((new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
			}.getValue(world, BlockPos.containing(x, y, z), "Working")) / MaxWorking));
		}
		return 0;
	}
}
