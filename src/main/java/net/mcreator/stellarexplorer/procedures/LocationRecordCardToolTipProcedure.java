package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class LocationRecordCardToolTipProcedure {
	public static String execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("isNoEmpty")) {
			return "\u00A77" + itemstack.getOrCreateTag().getDouble("X") + " " + itemstack.getOrCreateTag().getDouble("Y") + " " + itemstack.getOrCreateTag().getDouble("Z") + ", " + itemstack.getOrCreateTag().getString("blockType");
		}
		return Component.translatable("item.stellar_explorer.location_record_card.description_0").getString();
	}
}
