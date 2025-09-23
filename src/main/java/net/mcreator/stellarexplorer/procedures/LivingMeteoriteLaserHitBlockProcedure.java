package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;

public class LivingMeteoriteLaserHitBlockProcedure {
	public static void execute(LevelAccessor world, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.LIVING_METEORITE_LASER_TAIL.get()), (immediatesourceentity.getX()), (immediatesourceentity.getY()), (immediatesourceentity.getZ()), 12, 0, 0, 0, 0.05);
	}
}
