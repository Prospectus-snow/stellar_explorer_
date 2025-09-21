package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;

public class ShockWaveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		immediatesourceentity.setDeltaMovement(new Vec3(0, 0, 0));
		{
			Entity _ent = immediatesourceentity;
			_ent.teleportTo((x + immediatesourceentity.getPersistentData().getDouble("X")), y, (z + immediatesourceentity.getPersistentData().getDouble("Z")));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((x + immediatesourceentity.getPersistentData().getDouble("X")), y, (z + immediatesourceentity.getPersistentData().getDouble("Z")), _ent.getYRot(), _ent.getXRot());
		}
		immediatesourceentity.getPersistentData().putDouble("age", (immediatesourceentity.getPersistentData().getDouble("age") - 1));
		if (!world.getBlockState(BlockPos.containing(x, y - 0.8, z)).canOcclude()) {
			{
				Entity _ent = immediatesourceentity;
				_ent.teleportTo(x, (y - 0.8), z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(x, (y - 0.8), z, _ent.getYRot(), _ent.getXRot());
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y - 0.5, z)).canOcclude()) {
			{
				Entity _ent = immediatesourceentity;
				_ent.teleportTo(x, (y - 0.5), z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(x, (y - 0.5), z, _ent.getYRot(), _ent.getXRot());
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y - 0.1, z)).canOcclude()) {
			{
				Entity _ent = immediatesourceentity;
				_ent.teleportTo(x, (y - 0.1), z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(x, (y - 0.1), z, _ent.getYRot(), _ent.getXRot());
			}
		}
		if (immediatesourceentity.getPersistentData().getDouble("age") == 2 && immediatesourceentity.getPersistentData().getDouble("born") == 123) {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.VE_OUTPUT.get()), x, y, z, 1, 0, 0, 0, 0);
		BlockBreakParticleSpawnProcedure.execute(world, x, y, z, 0.4, 0, 3, 0);
		for (Entity entityiterator : world.getEntities(entity, new AABB((x + 4), (y + 4), (z + 4), (x - 4), (y - 4), (z - 4)))) {
			if (0.6 + entityiterator.getBbHeight() / 2 > Math.sqrt(((entityiterator.getY() + entityiterator.getBbHeight() / 2) - y) * ((entityiterator.getY() + entityiterator.getBbHeight() / 2) - y))
					&& 0.6 + entityiterator.getBbWidth() / 2 > Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z))
					&& (!(entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("chocolates_wandering_expansion:scrap_mob")))
							&& entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("chocolates_wandering_expansion:scrap_mob"))))
							|| entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null))
					&& (entityiterator instanceof Player || entityiterator instanceof Mob) && !(entityiterator == entity)) {
				entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("stellar_explorer:groundshock"))),
						immediatesourceentity, entity), (float) immediatesourceentity.getPersistentData().getDouble("damage"));
			}
		}
	}
}
