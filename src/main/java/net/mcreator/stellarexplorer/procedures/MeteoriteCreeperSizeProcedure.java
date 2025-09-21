package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;

public class MeteoriteCreeperSizeProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 100) {
			return 1 + 0.000035 * Math.pow(entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0, 1.5)
					+ 0.0006 * ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) - 100);
		}
		return 1 + Mth.nextDouble(RandomSource.create(), -0.000035, 0.000035) * Math.pow(entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0, 1.5);
	}
}
