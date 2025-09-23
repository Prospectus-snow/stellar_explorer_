package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;
import net.mcreator.stellarexplorer.entity.LivingMeteoriteEntity;

public class LivingMeteoriteLaserHitEntityProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
		if (entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.LIVING_METEORITE_LASER_TAIL.get()), (immediatesourceentity.getX()), (immediatesourceentity.getY()), (immediatesourceentity.getZ()), 12, 0, 0, 0, 0.05);
		if (entity instanceof LivingMeteoriteEntity) {
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(
						(float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + immediatesourceentity.getPersistentData().getDouble("damage") + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.05));
		} else {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), immediatesourceentity, sourceentity),
					(float) (immediatesourceentity.getPersistentData().getDouble("damage") + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.05));
		}
		if (!immediatesourceentity.level().isClientSide())
			immediatesourceentity.discard();
	}
}
