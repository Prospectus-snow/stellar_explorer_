package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.ItemStack;

public class LocationRecordCardRightClickAirProcedure {
	public static void execute(ItemStack itemstack) {
		itemstack.getOrCreateTag().putBoolean("isNoEmpty", false);
	}
}
