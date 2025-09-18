package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.ItemStack;

public class LocationRecordCardIsEmptyProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("isNoEmpty")) {
			return 1;
		}
		return 0;
	}
}
