package net.mcreator.stellarexplorer.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class RedstoneExtractorGUITextProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		BlockState blocktype = Blocks.AIR.defaultBlockState();
		blocktype = (world.getBlockState(BlockPos.containing(x, y, z)));
		return Component.translatable(("block.stellar_explorer." + (ForgeRegistries.BLOCKS.getKey(blocktype.getBlock()).toString()).substring((int) ((ForgeRegistries.BLOCKS.getKey(blocktype.getBlock()).toString()).indexOf(":", 0) + 1)))).getString();
	}
}
