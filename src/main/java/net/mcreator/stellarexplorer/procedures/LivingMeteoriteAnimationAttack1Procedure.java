package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.LivingMeteoriteEntity;

public class LivingMeteoriteAnimationAttack1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingMeteoriteEntity _datEntL0 && _datEntL0.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack)
				&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackType) : 0) == 1;
	}
}
