package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.LivingMeteoriteEntity;

public class LivingMeteoriteRandomWalkProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_HasTarget) : 0) <= 0;
	}
}
