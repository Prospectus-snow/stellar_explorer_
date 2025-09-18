package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

public class RedstoneExtractorGUITextProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		BlockState blocktype = Blocks.AIR.defaultBlockState();
		blocktype = (world.getBlockState(BlockPos.containing(x, y, z)));
		if (blocktype.getBlock() == StellarExplorerModBlocks.REDSTONE_EXTRACTOR.get()) {
			return Component.translatable("block.stellar_explorer.redstone_extractor").getString();
		} else if (blocktype.getBlock() == StellarExplorerModBlocks.VOID_TANK.get()) {
			return Component.translatable("block.stellar_explorer.void_tank").getString();
		}
		return Component.translatable("block.stellar_explorer.redstone_extractor").getString();
	}
}
