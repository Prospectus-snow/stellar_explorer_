package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

public class MachineVETooltipProcedure {
	public static String execute(ItemStack itemstack) {
		String VE = "";
		String VE2 = "";
		if (itemstack.getItem() == StellarExplorerModBlocks.REDSTONE_EXTRACTOR.get().asItem()) {
			VE = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvespeed").getString() + "\u00A754VE/5s";
			VE2 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvoidexudate").getString() + "\u00A75400";
		} else if (itemstack.getItem() == StellarExplorerModBlocks.VOID_TANK.get().asItem()) {
			VE2 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvoidexudate").getString() + "\u00A7540000";
			return VE2;
		}
		return VE + "\n" + VE2;
	}
}
