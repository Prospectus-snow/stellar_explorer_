package net.mcreator.stellarexplorer.procedures;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;
import net.mcreator.stellarexplorer.init.StellarExplorerModItems;
import net.mcreator.stellarexplorer.init.StellarExplorerModBlocks;

import java.util.concurrent.atomic.AtomicReference;

public class MachineVEOutputProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState targetBlock = Blocks.AIR.defaultBlockState();
		BlockState targetBlock2 = Blocks.AIR.defaultBlockState();
		BlockState targetBlock3 = Blocks.AIR.defaultBlockState();
		double MaxVoidExudate = 0;
		double targetX = 0;
		double targetY = 0;
		double targetZ = 0;
		double count = 0;
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 0)).getItem() == StellarExplorerModItems.LOCATION_RECORD_CARD.get() && (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 0)).getOrCreateTag().getBoolean("isNoEmpty")) {
			targetX = (new Object() {
				public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
					return _retval.get();
				}
			}.getItemStack(world, BlockPos.containing(x, y, z), 0)).getOrCreateTag().getDouble("X");
			targetY = (new Object() {
				public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
					return _retval.get();
				}
			}.getItemStack(world, BlockPos.containing(x, y, z), 0)).getOrCreateTag().getDouble("Y");
			targetZ = (new Object() {
				public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					BlockEntity _ent = world.getBlockEntity(pos);
					if (_ent != null)
						_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
					return _retval.get();
				}
			}.getItemStack(world, BlockPos.containing(x, y, z), 0)).getOrCreateTag().getDouble("Z");
			targetBlock = (world.getBlockState(BlockPos.containing(targetX, targetY, targetZ)));
			targetBlock2 = (world.getBlockState(BlockPos.containing(targetX, targetY - 1, targetZ)));
			targetBlock3 = (world.getBlockState(BlockPos.containing(x, y + 1, z)));
			if (targetBlock.getBlock() == StellarExplorerModBlocks.ENERGY_OUTPUT.get() && (targetBlock.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip14 ? targetBlock.getValue(_getip14) : -1) == 1
					&& targetBlock3.getBlock() == StellarExplorerModBlocks.ENERGY_OUTPUT.get() && (targetBlock3.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip16 ? targetBlock3.getValue(_getip16) : -1) == 1
					&& targetBlock2.is(BlockTags.create(new ResourceLocation("stellar_explorer:machine")))) {
				if (GetMaxVEProcedure.execute(world, targetX, targetY - 1, targetZ) > new Object() {
					public double getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getDouble(tag);
						return -1;
					}
				}.getValue(world, BlockPos.containing(targetX, targetY - 1, targetZ), "VoidExudate") && new Object() {
					public double getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getPersistentData().getDouble(tag);
						return -1;
					}
				}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate") > 0) {
					count = 0;
					for (int index0 = 0; index0 < 50; index0++) {
						count = count + 1;
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.VE_OUTPUT.get()), (x + 0.5 + ((targetX - x) / 50) * count), (y + 1.7 + ((targetY - (y + 1)) / 50) * count),
									(z + 0.5 + ((targetZ - z) / 50) * count), 1, 0, 0, 0, 0);
					}
				}
				for (int index1 = 0; index1 < 20; index1++) {
					if (GetMaxVEProcedure.execute(world, targetX, targetY - 1, targetZ) > new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getPersistentData().getDouble(tag);
							return -1;
						}
					}.getValue(world, BlockPos.containing(targetX, targetY - 1, targetZ), "VoidExudate")) {
						if (GetMaxVEProcedure.execute(world, targetX, targetY - 1, targetZ) > new Object() {
							public double getValue(LevelAccessor world, BlockPos pos, String tag) {
								BlockEntity blockEntity = world.getBlockEntity(pos);
								if (blockEntity != null)
									return blockEntity.getPersistentData().getDouble(tag);
								return -1;
							}
						}.getValue(world, BlockPos.containing(targetX, targetY - 1, targetZ), "VoidExudate") && new Object() {
							public double getValue(LevelAccessor world, BlockPos pos, String tag) {
								BlockEntity blockEntity = world.getBlockEntity(pos);
								if (blockEntity != null)
									return blockEntity.getPersistentData().getDouble(tag);
								return -1;
							}
						}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate") > 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(targetX, targetY - 1, targetZ);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("VoidExudate", (new Object() {
										public double getValue(LevelAccessor world, BlockPos pos, String tag) {
											BlockEntity blockEntity = world.getBlockEntity(pos);
											if (blockEntity != null)
												return blockEntity.getPersistentData().getDouble(tag);
											return -1;
										}
									}.getValue(world, BlockPos.containing(targetX, targetY - 1, targetZ), "VoidExudate") + 1));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getPersistentData().putDouble("VoidExudate", ((new Object() {
										public double getValue(LevelAccessor world, BlockPos pos, String tag) {
											BlockEntity blockEntity = world.getBlockEntity(pos);
											if (blockEntity != null)
												return blockEntity.getPersistentData().getDouble(tag);
											return -1;
										}
									}.getValue(world, BlockPos.containing(x, y, z), "VoidExudate")) - 1));
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
			}
		}
	}
}
