package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

public class LocationRecordCardRightClickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		itemstack.getOrCreateTag().putBoolean("isNoEmpty", true);
		itemstack.getOrCreateTag().putDouble("X", x);
		itemstack.getOrCreateTag().putDouble("Y", y);
		itemstack.getOrCreateTag().putDouble("Z", z);
		itemstack.getOrCreateTag().putString("blockType", (((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock())).getDisplayName().getString()).substring(1,
				(int) (((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock())).getDisplayName().getString()).length() - 1))));
	}
}
