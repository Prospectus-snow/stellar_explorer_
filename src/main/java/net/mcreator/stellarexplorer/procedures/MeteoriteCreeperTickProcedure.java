package net.mcreator.stellarexplorer.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;

public class MeteoriteCreeperTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		entity.clearFire();
		if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_HasTarget) : 0) > 0) {
			if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_HasTarget, (int) ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_HasTarget) : 0) - 1));
			if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_Pathfinding, true);
		} else if (entity instanceof MeteoriteCreeperEntity _datEntL5 && _datEntL5.getEntityData().get(MeteoriteCreeperEntity.DATA_Pathfinding)) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_Pathfinding, false);
		}
		if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_HasTarget) : 0) > 0
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2) {
			if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_InExplode, true);
		} else {
			if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_InExplode, false);
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (996 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
					.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
				if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_HasTarget, 4);
			}
		}
		if (entity instanceof MeteoriteCreeperEntity _datEntL31 && _datEntL31.getEntityData().get(MeteoriteCreeperEntity.DATA_InAttack)) {
			if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_AttackTick, (int) ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_AttackTick) : 0) + 1));
		}
		if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) <= 100) {
			if (entity instanceof MeteoriteCreeperEntity _datEntL35 && _datEntL35.getEntityData().get(MeteoriteCreeperEntity.DATA_InExplode)) {
				if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_ExplodeTick, (int) ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) + 1));
				if (entity instanceof LivingEntity _livingEntity39 && _livingEntity39.getAttributes().hasAttribute(Attributes.ARMOR))
					_livingEntity39.getAttribute(Attributes.ARMOR)
							.setBaseValue(((entity instanceof LivingEntity _livingEntity38 && _livingEntity38.getAttributes().hasAttribute(Attributes.ARMOR) ? _livingEntity38.getAttribute(Attributes.ARMOR).getBaseValue() : 0) + 0.2));
				if (entity instanceof LivingEntity _livingEntity41 && _livingEntity41.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
					_livingEntity41.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(
							((entity instanceof LivingEntity _livingEntity40 && _livingEntity40.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS) ? _livingEntity40.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() : 0) + 0.1));
			} else {
				for (int index0 = 0; index0 < 5; index0++) {
					if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 1) {
						if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_ExplodeTick, (int) ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) - 1));
						if (entity instanceof LivingEntity _livingEntity46 && _livingEntity46.getAttributes().hasAttribute(Attributes.ARMOR))
							_livingEntity46.getAttribute(Attributes.ARMOR)
									.setBaseValue(((entity instanceof LivingEntity _livingEntity45 && _livingEntity45.getAttributes().hasAttribute(Attributes.ARMOR) ? _livingEntity45.getAttribute(Attributes.ARMOR).getBaseValue() : 0) - 0.2));
						if (entity instanceof LivingEntity _livingEntity48 && _livingEntity48.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
							_livingEntity48.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(
									((entity instanceof LivingEntity _livingEntity47 && _livingEntity47.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS) ? _livingEntity47.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() : 0) - 0.1));
					}
				}
			}
		} else {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_ExplodeTick, (int) ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) + 10));
				if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 150) {
					if (world instanceof Level _level && !_level.isClientSide())
						_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.MOB);
					if (!entity.level().isClientSide())
						entity.discard();
				}
			}
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().moveTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 1.2);
			if (!(entity instanceof MeteoriteCreeperEntity _datEntL64 && _datEntL64.getEntityData().get(MeteoriteCreeperEntity.DATA_InAttack)) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (1.3 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
						_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_InAttack, true);
				}
			}
		}
		if (entity instanceof MeteoriteCreeperEntity _datEntL89 && _datEntL89.getEntityData().get(MeteoriteCreeperEntity.DATA_InAttack)) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
			}
			if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_AttackTick) : 0) == 12) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 6), (y - 1), (z - 6), (x + 6), (y + 3), (z + 6)))) {
						if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
							if (1.4 + (entity.getBbWidth() + entityiterator.getBbWidth()) / 2 > Math
									.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getY() - y) * (entityiterator.getY() - y) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z))) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.EXPLOSION), entity),
										(float) (entity instanceof LivingEntity _livingEntity111 && _livingEntity111.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity111.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0));
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY() + entityiterator.getBbHeight() / 2, entityiterator.getZ()),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1, false);
									}
								}
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.EXPLOSION, (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, 0, 0, 0, 0);
								entityiterator.push(0, 1, 0);
							}
						}
					}
				}
			}
			if ((entity instanceof MeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(MeteoriteCreeperEntity.DATA_AttackTick) : 0) > 25) {
				if (entity instanceof MeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(MeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof MeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(MeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
	}
}
