
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.stellarexplorer.block.entity.VoidTankBlockEntity;
import net.mcreator.stellarexplorer.block.entity.RedstoneExtractorBlockEntity;
import net.mcreator.stellarexplorer.block.entity.EnergyOutputBlockEntity;
import net.mcreator.stellarexplorer.StellarExplorerMod;

public class StellarExplorerModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StellarExplorerMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> VOID_TANK = register("void_tank", StellarExplorerModBlocks.VOID_TANK, VoidTankBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> REDSTONE_EXTRACTOR = register("redstone_extractor", StellarExplorerModBlocks.REDSTONE_EXTRACTOR, RedstoneExtractorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ENERGY_OUTPUT = register("energy_output", StellarExplorerModBlocks.ENERGY_OUTPUT, EnergyOutputBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
