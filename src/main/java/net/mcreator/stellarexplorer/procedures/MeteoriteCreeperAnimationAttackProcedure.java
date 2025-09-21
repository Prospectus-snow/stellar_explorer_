package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;

public class MeteoriteCreeperAnimationAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof MeteoriteCreeperEntity _datEntL0 && _datEntL0.getEntityData().get(MeteoriteCreeperEntity.DATA_InAttack);
	}
}
