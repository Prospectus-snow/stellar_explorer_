package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

public class AberrantMeteoriteCreeperAngryProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_HasTarget) : 0) > 0
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 0;
	}
}
