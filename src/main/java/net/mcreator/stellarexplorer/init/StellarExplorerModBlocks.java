
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.stellarexplorer.block.XenithystOreBlock;
import net.mcreator.stellarexplorer.block.VoidTankBlock;
import net.mcreator.stellarexplorer.block.RedstoneExtractorBlock;
import net.mcreator.stellarexplorer.block.EnergyOutputBlock;
import net.mcreator.stellarexplorer.block.DeepslateXenithystOreBlock;
import net.mcreator.stellarexplorer.StellarExplorerMod;

public class StellarExplorerModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, StellarExplorerMod.MODID);
	public static final RegistryObject<Block> XENITHYST_ORE = REGISTRY.register("xenithyst_ore", () -> new XenithystOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_XENITHYST_ORE = REGISTRY.register("deepslate_xenithyst_ore", () -> new DeepslateXenithystOreBlock());
	public static final RegistryObject<Block> VOID_TANK = REGISTRY.register("void_tank", () -> new VoidTankBlock());
	public static final RegistryObject<Block> REDSTONE_EXTRACTOR = REGISTRY.register("redstone_extractor", () -> new RedstoneExtractorBlock());
	public static final RegistryObject<Block> ENERGY_OUTPUT = REGISTRY.register("energy_output", () -> new EnergyOutputBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
