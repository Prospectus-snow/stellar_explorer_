package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

public class AberrantMeteoriteCreeperAnimationAttack1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof AberrantMeteoriteCreeperEntity _datEntL0 && _datEntL0.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 1;
	}
}
