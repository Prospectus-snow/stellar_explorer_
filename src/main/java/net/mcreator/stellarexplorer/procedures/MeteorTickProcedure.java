package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;
import net.mcreator.stellarexplorer.entity.MeteorEntity;

public class MeteorTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double targetX = 0;
		double targetY = 0;
		double targetZ = 0;
		entity.setDeltaMovement(new Vec3(0, (-2), 0));
		entity.fallDistance = 0;
		world.addParticle((SimpleParticleType) (StellarExplorerModParticleTypes.BIG_METEOR_SMOKE.get()), x, y, z, (0 + Mth.nextDouble(RandomSource.create(), -0.06, 0.06)), 0, (0 + Mth.nextDouble(RandomSource.create(), -0.06, 0.06)));
		if ((entity instanceof MeteorEntity _datEntI ? _datEntI.getEntityData().get(MeteorEntity.DATA_SummonFire) : 0) == 0) {
			if (entity.onGround()) {
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, y, z, 16, Level.ExplosionInteraction.TNT);
				if (entity instanceof MeteorEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteorEntity.DATA_SummonFire, 1);
				for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 3, 5); index0++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = StellarExplorerModEntities.METEORITE_CREEPER.get().spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -0.85, 0.85), y, z + Mth.nextDouble(RandomSource.create(), -0.85, 0.85)),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setDeltaMovement((Mth.nextDouble(RandomSource.create(), -0.85, 0.85)), (Mth.nextDouble(RandomSource.create(), 1.2, 1.4)), (Mth.nextDouble(RandomSource.create(), -0.85, 0.85)));
						}
					}
				}
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setDeltaMovement(0, 0, 0);
					}
				}
			}
		} else {
			for (int index1 = 0; index1 < 300; index1++) {
				targetX = x + Mth.nextInt(RandomSource.create(), -18, 18);
				targetY = y + Mth.nextInt(RandomSource.create(), -10, 3);
				targetZ = z + Mth.nextInt(RandomSource.create(), -18, 18);
				while (!(((world.getBlockState(BlockPos.containing(targetX, targetY, targetZ))).getBlock() == Blocks.FIRE || (world.getBlockState(BlockPos.containing(targetX, targetY, targetZ))).getBlock() == Blocks.AIR)
						&& Blocks.FIRE.defaultBlockState().canSurvive(world, BlockPos.containing(targetX, targetY, targetZ)))
						&& (Mth.nextInt(RandomSource.create(), 1, 100) < 100 * (Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetZ - z, 2)) / 22)
								|| (entity instanceof MeteorEntity _datEntI ? _datEntI.getEntityData().get(MeteorEntity.DATA_TryNumber) : 0) < 20)) {
					targetX = x + Mth.nextInt(RandomSource.create(), -14, 14);
					targetY = y + Mth.nextInt(RandomSource.create(), -10, 3);
					targetZ = z + Mth.nextInt(RandomSource.create(), -14, 14);
					if (entity instanceof MeteorEntity _datEntSetI)
						_datEntSetI.getEntityData().set(MeteorEntity.DATA_TryNumber, (int) ((entity instanceof MeteorEntity _datEntI ? _datEntI.getEntityData().get(MeteorEntity.DATA_TryNumber) : 0) + 1));
				}
				if (entity instanceof MeteorEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteorEntity.DATA_TryNumber, 0);
				if (Mth.nextInt(RandomSource.create(), 1, (int) (100 - (100 * (Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetZ - z, 2)) / 22)) / 3)) > 100 * (Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetZ - z, 2)) / 22)
						&& ((world.getBlockState(BlockPos.containing(targetX, targetY, targetZ))).getBlock() == Blocks.FIRE || (world.getBlockState(BlockPos.containing(targetX, targetY, targetZ))).getBlock() == Blocks.AIR)
						&& Blocks.FIRE.defaultBlockState().canSurvive(world, BlockPos.containing(targetX, targetY, targetZ))) {
					world.setBlock(BlockPos.containing(targetX, targetY, targetZ), Blocks.FIRE.defaultBlockState(), 3);
				}
			}
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}
