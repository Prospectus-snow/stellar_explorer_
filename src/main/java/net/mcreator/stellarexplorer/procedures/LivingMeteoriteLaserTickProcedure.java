package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;

public class LivingMeteoriteLaserTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		immediatesourceentity.getPersistentData().putDouble("age", (immediatesourceentity.getPersistentData().getDouble("age") - 1));
		if (immediatesourceentity.getPersistentData().getDouble("age") == 2 && immediatesourceentity.getPersistentData().getDouble("born") == 123) {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
		world.addParticle((SimpleParticleType) (StellarExplorerModParticleTypes.LIVING_METEORITE_LASER_TOP.get()), x, y, z, 0, 0, 0);
		world.addParticle((SimpleParticleType) (StellarExplorerModParticleTypes.LIVING_METEORITE_LASER_TAIL.get()), x, y, z, (immediatesourceentity.getDeltaMovement().x() * 0.6), (immediatesourceentity.getDeltaMovement().y() * 0.6),
				(immediatesourceentity.getDeltaMovement().z() * 0.6));
	}
}
