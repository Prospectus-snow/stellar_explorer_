package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;

public class MeteoriteCreeperRandomWalkProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_HasTarget) : 0) <= 0;
	}
}
