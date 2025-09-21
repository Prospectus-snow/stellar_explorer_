package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

public class AberrantMeteoriteCreeperSizeProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 200) {
			return 1.1 + (-0.0000175) * Math.pow(entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0, 1.5)
					+ 0.0006 * ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) - 200);
		}
		return 1.1 + Mth.nextDouble(RandomSource.create(), -0.0000175, 0.0000175) * Math.pow(entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0, 1.5);
	}
}
