package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

public class MachineVETooltipProcedure {
	public static String execute(ItemStack itemstack) {
		String VE = "";
		String VE2 = "";
		String VE3 = "";
		if (itemstack.getItem() == StellarExplorerModBlocks.REDSTONE_EXTRACTOR.get().asItem()) {
			VE = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvespeed").getString() + "\u00A754VE/5s";
			VE2 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvoidexudate").getString() + "\u00A75400VE";
		} else if (itemstack.getItem() == StellarExplorerModBlocks.VOID_TANK.get().asItem()) {
			VE2 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvoidexudate").getString() + "\u00A7540000VE";
			return VE2;
		} else if (itemstack.getItem() == StellarExplorerModBlocks.METEOR_ATTRACTOR.get().asItem()) {
			VE = "\u00A77" + Component.translatable("item.stellar_explorer.machine.operating_cycle").getString() + "\u00A7520s";
			VE3 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.consumption_rate").getString() + "\u00A75180VE/s";
			VE2 = "\u00A77" + Component.translatable("item.stellar_explorer.machine.maxvoidexudate").getString() + "\u00A751200VE";
			return VE3 + "\n" + VE + "\n" + VE2;
		}
		return VE + "\n" + VE2;
	}
}
