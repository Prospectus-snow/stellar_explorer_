package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;

public class AberrantMeteoriteCreeperProjectileTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		immediatesourceentity.getPersistentData().putDouble("age", (immediatesourceentity.getPersistentData().getDouble("age") - 1));
		if (immediatesourceentity.getPersistentData().getDouble("age") == 2 && immediatesourceentity.getPersistentData().getDouble("born") == 123) {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.ABERRANT_METEORITE_CREEPER_BOMB.get()), x, y, z, 1, 0, 0, 0, 0);
	}
}
