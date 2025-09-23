package net.mcreator.stellarexplorer.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.stellarexplorer.init.StellarExplorerModParticleTypes;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;
import net.mcreator.stellarexplorer.entity.LivingMeteoriteLaserEntity;
import net.mcreator.stellarexplorer.entity.LivingMeteoriteEntity;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

import java.util.List;
import java.util.Comparator;

public class LivingMeteoriteTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double fx = 0;
		double attack_type = 0;
		entity.clearFire();
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.METEOR_SMOKE.get()), (x + entity.getDeltaMovement().x() * (-2.5)), (y + entity.getBbHeight() / 2 + entity.getDeltaMovement().y() * (-2.5)),
					(z + entity.getDeltaMovement().z() * (-2.5)), 1, 0, 0, 0, 0.02);
		if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_HasTarget) : 0) > 0) {
			if (entity instanceof LivingMeteoriteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_HasTarget, (int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_HasTarget) : 0) - 1));
			if (entity instanceof LivingMeteoriteEntity _datEntSetL)
				_datEntSetL.getEntityData().set(LivingMeteoriteEntity.DATA_Pathfinding, true);
		} else if (entity instanceof LivingMeteoriteEntity _datEntL10 && _datEntL10.getEntityData().get(LivingMeteoriteEntity.DATA_Pathfinding)) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if (entity instanceof LivingMeteoriteEntity _datEntSetL)
				_datEntSetL.getEntityData().set(LivingMeteoriteEntity.DATA_Pathfinding, false);
		} else {
			if (entity instanceof LivingMeteoriteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_CanRange, 80);
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (996 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
					.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_HasTarget, 4);
			}
		}
		if (entity instanceof LivingMeteoriteEntity _datEntL32 && _datEntL32.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack)) {
			if (entity instanceof LivingMeteoriteEntity _datEntSetI)
				_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackTick, (int) ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) + 1));
		} else {
			if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_CanRange) : 0) > 0) {
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_CanRange, (int) ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_CanRange) : 0) - 1));
			}
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (!(entity instanceof LivingMeteoriteEntity _datEntL40 && _datEntL40.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack))) {
				if (entity instanceof Mob _entity)
					_entity.getNavigation().moveTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 1.2);
			}
			if (!(entity instanceof LivingMeteoriteEntity _datEntL48 && _datEntL48.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack)) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (4 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof LivingMeteoriteEntity _datEntSetL)
						_datEntSetL.getEntityData().set(LivingMeteoriteEntity.DATA_InAttack, true);
					if (entity instanceof LivingMeteoriteEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackType, 2);
				} else if (12 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))
						&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_CanRange) : 0) <= 0) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof LivingMeteoriteEntity _datEntSetL)
						_datEntSetL.getEntityData().set(LivingMeteoriteEntity.DATA_InAttack, true);
					if (entity instanceof LivingMeteoriteEntity _datEntSetI)
						_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackType, 1);
				}
			}
		}
		if (entity instanceof LivingMeteoriteEntity _datEntL99 && _datEntL99.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack)
				&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackType) : 0) == 1) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
			}
			if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) == 10) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					entity.push((entity.getLookAngle().x * (-0.3)), (entity.getLookAngle().y * (-0.3)), (entity.getLookAngle().z * (-0.3)));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.bell.use")), SoundSource.HOSTILE, 1, 8);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.bell.use")), SoundSource.HOSTILE, 1, 8, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (StellarExplorerModParticleTypes.LIVING_METEORITE_LASER_TAIL.get()), x, (y + entity.getBbHeight() / 2), z, 12, 0, 0, 0, 0.1);
					{
						Entity _shootFrom = entity;
						Level projectileLevel = _shootFrom.level();
						if (!projectileLevel.isClientSide()) {
							Projectile _entityToSpawn = new Object() {
								public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
									AbstractArrow entityToSpawn = new LivingMeteoriteLaserEntity(StellarExplorerModEntities.LIVING_METEORITE_LASER.get(), level);
									entityToSpawn.setOwner(shooter);
									entityToSpawn.setBaseDamage(damage);
									entityToSpawn.setKnockback(knockback);
									entityToSpawn.setSilent(true);
									return entityToSpawn;
								}
							}.getArrow(projectileLevel, entity, 0, 0);
							_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
							_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, (float) 0.8, 0);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingMeteoriteLaserEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
								entityiterator.setNoGravity(true);
								entityiterator.getPersistentData().putDouble("born", 123);
								entityiterator.getPersistentData().putDouble("age", 102);
								entityiterator.getPersistentData().putDouble("damage",
										(0.5 * (entity instanceof LivingEntity _livingEntity127 && _livingEntity127.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity127.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
							}
						}
					}
				}
			}
			if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) > 25) {
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackTick, 0);
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_CanRange, 60);
				if (entity instanceof LivingMeteoriteEntity _datEntSetL)
					_datEntSetL.getEntityData().set(LivingMeteoriteEntity.DATA_InAttack, false);
			}
		}
		if (entity instanceof LivingMeteoriteEntity _datEntL134 && _datEntL134.getEntityData().get(LivingMeteoriteEntity.DATA_InAttack)
				&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackType) : 0) == 2) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
				}
				if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) > 22
						&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) < 51) {
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 0.3), (entity.getLookAngle().y * 0.3), (entity.getLookAngle().z * 0.3)));
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 12), (y - 12), (z - 12), (x + 12), (y + 12), (z + 12)))) {
						if ((entityiterator instanceof Mob || entityiterator instanceof Player) && !(entityiterator instanceof LivingMeteoriteEntity)) {
							if (0.3 + (entityiterator.getBbWidth() + entity.getBbWidth()) / 2 > Math.sqrt(Math.pow(entityiterator.getX() - x, 2) + Math.pow(entityiterator.getY() - y, 2) + Math.pow(entityiterator.getZ() - z, 2))) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
										(float) ((entity instanceof LivingEntity _livingEntity161 && _livingEntity161.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity161.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)
												* 0.5));
							}
						}
					}
				}
				if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) > 55
						&& (entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) < 66) {
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * (-0.2)), (entity.getLookAngle().y * (-0.2)), (entity.getLookAngle().z * (-0.2))));
				}
			}
			if ((entity instanceof LivingMeteoriteEntity _datEntI ? _datEntI.getEntityData().get(LivingMeteoriteEntity.DATA_AttackTick) : 0) > 66) {
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackTick, 0);
				if (entity instanceof LivingMeteoriteEntity _datEntSetI)
					_datEntSetI.getEntityData().set(LivingMeteoriteEntity.DATA_AttackType, 1);
			}
		}
	}
}
