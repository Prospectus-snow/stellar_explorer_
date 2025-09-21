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
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;
import net.mcreator.stellarexplorer.entity.ShockWaveEntity;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperProjectileEntity;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

import java.util.List;
import java.util.Comparator;

public class AberrantMeteoriteCreeperTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double fx = 0;
		double attack_type = 0;
		entity.clearFire();
		if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_HasTarget) : 0) > 0) {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_HasTarget, (int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_HasTarget) : 0) - 1));
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_Pathfinding, true);
		} else if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL5 && _datEntL5.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_Pathfinding)) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_Pathfinding, false);
		} else {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanRange, 100);
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanJump, 100);
		}
		if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_HasTarget) : 0) > 0
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 6) {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InExplode, true);
		} else {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InExplode, false);
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (996 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
					.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
							+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_HasTarget, 4);
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL33 && _datEntL33.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)) {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick,
						(int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) + 1));
		} else {
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanRange) : 0) > 0) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanRange,
							(int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanRange) : 0) - 1));
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanJump) : 0) > 0) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanJump, (int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanJump) : 0) - 1));
			}
		}
		if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) <= 200) {
			if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL43 && _datEntL43.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InExplode)) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick,
							(int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) + 1));
				if (entity instanceof LivingEntity _livingEntity47 && _livingEntity47.getAttributes().hasAttribute(Attributes.ARMOR))
					_livingEntity47.getAttribute(Attributes.ARMOR)
							.setBaseValue(((entity instanceof LivingEntity _livingEntity46 && _livingEntity46.getAttributes().hasAttribute(Attributes.ARMOR) ? _livingEntity46.getAttribute(Attributes.ARMOR).getBaseValue() : 0) + 0.1));
				if (entity instanceof LivingEntity _livingEntity49 && _livingEntity49.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
					_livingEntity49.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(
							((entity instanceof LivingEntity _livingEntity48 && _livingEntity48.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS) ? _livingEntity48.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() : 0) + 0.05));
			} else {
				for (int index0 = 0; index0 < 5; index0++) {
					if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 1) {
						if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
							_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick,
									(int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) - 1));
						if (entity instanceof LivingEntity _livingEntity54 && _livingEntity54.getAttributes().hasAttribute(Attributes.ARMOR))
							_livingEntity54.getAttribute(Attributes.ARMOR)
									.setBaseValue(((entity instanceof LivingEntity _livingEntity53 && _livingEntity53.getAttributes().hasAttribute(Attributes.ARMOR) ? _livingEntity53.getAttribute(Attributes.ARMOR).getBaseValue() : 0) - 0.1));
						if (entity instanceof LivingEntity _livingEntity56 && _livingEntity56.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
							_livingEntity56.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(
									((entity instanceof LivingEntity _livingEntity55 && _livingEntity55.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS) ? _livingEntity55.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() : 0) - 0.05));
					}
				}
			}
		} else {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick,
							(int) ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) + 10));
				if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_ExplodeTick) : 0) > 250) {
					if (world instanceof Level _level && !_level.isClientSide())
						_level.explode(null, x, y, z, 7, Level.ExplosionInteraction.MOB);
					if (!entity.level().isClientSide())
						entity.discard();
				}
			}
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (!(entity instanceof AberrantMeteoriteCreeperEntity _datEntL65 && _datEntL65.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack))
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 2
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 4) {
				if (entity instanceof Mob _entity)
					_entity.getNavigation().moveTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 1.2);
			}
			if (!(entity instanceof AberrantMeteoriteCreeperEntity _datEntL75 && _datEntL75.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)) && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
				if (2 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, true);
					fx = Mth.nextInt(RandomSource.create(), 1, 4);
					while ((fx == 5 || fx == 1) && (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == fx
							|| ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 5
									|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 6) && fx == 4) {
						fx = Mth.nextInt(RandomSource.create(), 1, 4);
					}
					if (fx == 1 && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2) {
						fx = 3;
					} else if (fx == 3 && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2) {
						fx = 2;
					} else if (fx == 4) {
						fx = 5;
					}
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackType, (int) fx);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanRange, 100);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanJump, 100);
				} else if (6 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))
						&& 3 > Math.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() - y))
						&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanJump) : 0) <= 0) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, true);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackType, 5);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanRange, 100);
				} else if (24 + (entity.getBbWidth() + (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getBbWidth()) / 2 > Math
						.sqrt(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - x)
								+ ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z) * ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - z))
						&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanRange) : 0) <= 0) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
							((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, true);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackType, 4);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanRange, 60);
				}
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL160 && _datEntL160.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 1) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 15) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					BlockBreakParticleSpawn2Procedure.execute(world, x + entity.getLookAngle().x * 1.75, y, z + entity.getLookAngle().z * 1.75, 2.1, 24, 0.2, 0.1);
					fx = (Math.toDegrees(Math.atan(((-1) * entity.getLookAngle().x) / (entity.getLookAngle().z + 0.000001))) + 180 * (Math.signum(entity.getLookAngle().z) / 2)) - 110;
					for (int index2 = 0; index2 < 12; index2++) {
						fx = fx + 30;
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = new Object() {
								public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
									AbstractArrow entityToSpawn = new ShockWaveEntity(StellarExplorerModEntities.SHOCK_WAVE.get(), level);
									entityToSpawn.setOwner(shooter);
									entityToSpawn.setBaseDamage(damage);
									entityToSpawn.setKnockback(knockback);
									entityToSpawn.setSilent(true);
									return entityToSpawn;
								}
							}.getArrow(projectileLevel, entity, 5, 1);
							_entityToSpawn.setPos((x + entity.getLookAngle().x * 1.75), y, (z + entity.getLookAngle().z * 1.75));
							_entityToSpawn.shoot(0, 0, 0, 0, 0);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
						{
							final Vec3 _center = new Vec3((x + entity.getLookAngle().x * 1.75), y, (z + entity.getLookAngle().z * 1.75));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof ShockWaveEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
									entityiterator.getPersistentData().putDouble("born", 123);
									entityiterator.getPersistentData().putDouble("age", 23);
									entityiterator.getPersistentData().putDouble("damage", (0.75
											* (entity instanceof LivingEntity _livingEntity180 && _livingEntity180.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity180.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
									entityiterator.getPersistentData().putDouble("X", (0.28 * Math.cos((fx * 3.1415) / 180)));
									entityiterator.getPersistentData().putDouble("Z", (0.28 * Math.sin((fx * 3.1415) / 180)));
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x + entity.getLookAngle().x, y, z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE,
									1, 1);
						} else {
							_level.playLocalSound((x + entity.getLookAngle().x), y, (z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 12), (y - 1), (z - 12), (x + 12), (y + 3), (z + 12)))) {
						if (entityiterator instanceof Mob || entityiterator instanceof Player) {
							if (1.9 + entityiterator.getBbWidth() / 2 > Math
									.sqrt(Math.pow(entityiterator.getX() - (x + entity.getLookAngle().x * 1.75), 2) + Math.pow(entityiterator.getY() - y, 2) + Math.pow(entityiterator.getZ() - (z + entity.getLookAngle().z * 1.75), 2))) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("stellar_explorer:groundshock"))), entity),
										(float) (entity instanceof LivingEntity _livingEntity196 && _livingEntity196.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity196.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0));
								if ((entityiterator instanceof LivingEntity _livingEntity199 && _livingEntity199.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
										? _livingEntity199.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
										: 0) < 1) {
									entityiterator.push(0,
											(0.6 * (1 - (entityiterator instanceof LivingEntity _livingEntity200 && _livingEntity200.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
													? _livingEntity200.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
													: 0))),
											0);
								}
							}
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 35) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL206 && _datEntL206.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 2) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 17) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 12), (y - 1), (z - 12), (x + 12), (y + 3), (z + 12)))) {
						if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
							if (2 + (entity.getBbWidth() + entityiterator.getBbWidth()) / 2 > Math.sqrt(Math.pow(entityiterator.getX() - x, 2) + Math.pow(entityiterator.getY() - y, 2) + Math.pow(entityiterator.getZ() - z, 2))
									&& ((entity.getLookAngle().x / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
											* ((entityiterator.getX() - x) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z)))
											+ (entity.getLookAngle().z / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
													* ((entityiterator.getZ() - z) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z))) > 1
											&& Math.acos(1) <= (80 * 3.14159) / 360
											|| (entity.getLookAngle().x / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
													* ((entityiterator.getX() - x) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z)))
													+ (entity.getLookAngle().z / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
															* ((entityiterator.getZ() - z) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z))) < -1
													&& Math.acos(-1) <= (80 * 3.14159) / 360
											|| Math.acos((entity.getLookAngle().x / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
													* ((entityiterator.getX() - x) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z)))
													+ (entity.getLookAngle().z / Math.sqrt(entity.getLookAngle().x * entity.getLookAngle().x + entity.getLookAngle().z * entity.getLookAngle().z))
															* ((entityiterator.getZ() - z) / Math.sqrt((entityiterator.getX() - x) * (entityiterator.getX() - x) + (entityiterator.getZ() - z) * (entityiterator.getZ() - z)))) <= (80 * 3.14159)
																	/ 360)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.EXPLOSION), entity),
										(float) ((entity instanceof LivingEntity _livingEntity286 && _livingEntity286.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity286.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)
												* 1.25));
								entityiterator.push(0, 0.8, 0);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() / 2), (entityiterator.getZ()), 1, Level.ExplosionInteraction.NONE);
							}
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 43) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL299 && _datEntL299.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 3) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 15
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 28
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 38) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					BlockBreakParticleSpawn2Procedure.execute(world, x + entity.getLookAngle().x * 1.75, y, z + entity.getLookAngle().z * 1.75, 2.1, 24, 0.2, 0.1);
					fx = (Math.toDegrees(Math.atan(((-1) * entity.getLookAngle().x) / (entity.getLookAngle().z + 0.000001))) + 180 * (Math.signum(entity.getLookAngle().z) / 2)) - 110;
					for (int index3 = 0; index3 < 12; index3++) {
						fx = fx + 30;
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = new Object() {
								public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
									AbstractArrow entityToSpawn = new ShockWaveEntity(StellarExplorerModEntities.SHOCK_WAVE.get(), level);
									entityToSpawn.setOwner(shooter);
									entityToSpawn.setBaseDamage(damage);
									entityToSpawn.setKnockback(knockback);
									entityToSpawn.setSilent(true);
									return entityToSpawn;
								}
							}.getArrow(projectileLevel, entity, 5, 1);
							_entityToSpawn.setPos((x + entity.getLookAngle().x * 1.75), y, (z + entity.getLookAngle().z * 1.75));
							_entityToSpawn.shoot(0, 0, 0, 0, 0);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
						{
							final Vec3 _center = new Vec3((x + entity.getLookAngle().x * 1.75), y, (z + entity.getLookAngle().z * 1.75));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof ShockWaveEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
									entityiterator.getPersistentData().putDouble("born", 123);
									entityiterator.getPersistentData().putDouble("age", 18);
									entityiterator.getPersistentData().putDouble("damage", (0.75
											* (entity instanceof LivingEntity _livingEntity321 && _livingEntity321.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity321.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
									entityiterator.getPersistentData().putDouble("X", (0.33 * Math.cos((fx * 3.1415) / 180)));
									entityiterator.getPersistentData().putDouble("Z", (0.33 * Math.sin((fx * 3.1415) / 180)));
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x + entity.getLookAngle().x, y, z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE,
									1, 1);
						} else {
							_level.playLocalSound((x + entity.getLookAngle().x), y, (z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 12), (y - 1), (z - 12), (x + 12), (y + 3), (z + 12)))) {
						if (entityiterator instanceof Mob || entityiterator instanceof Player) {
							if (1.9 + entityiterator.getBbWidth() / 2 > Math
									.sqrt(Math.pow(entityiterator.getX() - (x + entity.getLookAngle().x * 1.75), 2) + Math.pow(entityiterator.getY() - y, 2) + Math.pow(entityiterator.getZ() - (z + entity.getLookAngle().z * 1.75), 2))) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("stellar_explorer:groundshock"))), entity),
										(float) (entity instanceof LivingEntity _livingEntity337 && _livingEntity337.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity337.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0));
								if ((entityiterator instanceof LivingEntity _livingEntity340 && _livingEntity340.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
										? _livingEntity340.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
										: 0) < 1) {
									entityiterator.push(0,
											(0.6 * (1 - (entityiterator instanceof LivingEntity _livingEntity341 && _livingEntity341.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
													? _livingEntity341.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
													: 0))),
											0);
								}
							}
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 58) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL347 && _datEntL347.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 4) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY() + 1),
						((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 11
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 18
					|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 30) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					fx = (Math.toDegrees(Math.atan(((-1) * entity.getLookAngle().x) / (entity.getLookAngle().z + 0.000001))) + 180 * (Math.signum(entity.getLookAngle().z) / 2)) - 0;
					for (int index4 = 0; index4 < 1; index4++) {
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = new Object() {
								public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
									AbstractArrow entityToSpawn = new AberrantMeteoriteCreeperProjectileEntity(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), level);
									entityToSpawn.setOwner(shooter);
									entityToSpawn.setBaseDamage(damage);
									entityToSpawn.setKnockback(knockback);
									entityToSpawn.setSilent(true);
									return entityToSpawn;
								}
							}.getArrow(projectileLevel, entity, 0, 0);
							_entityToSpawn.setPos((x + entity.getLookAngle().x * 2.25), (y + 2.7), (z + entity.getLookAngle().z * 2.25));
							_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z), 1, 0);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
						{
							final Vec3 _center = new Vec3((x + entity.getLookAngle().x * 2.25), (y + 2.7), (z + entity.getLookAngle().z * 2.25));
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof AberrantMeteoriteCreeperProjectileEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
									entityiterator.setNoGravity(true);
									entityiterator.getPersistentData().putDouble("born", 123);
									entityiterator.getPersistentData().putDouble("age", 102);
									entityiterator.getPersistentData().putDouble("damage", (0.75
											* (entity instanceof LivingEntity _livingEntity379 && _livingEntity379.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity379.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x + entity.getLookAngle().x, y, z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound((x + entity.getLookAngle().x), y, (z + entity.getLookAngle().z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 45) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL388 && _datEntL388.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 5) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 17) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2
							|| entity instanceof AberrantMeteoriteCreeperEntity _datEntL395 && _datEntL395.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InExplode)
							|| (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_CanJump) : 0) <= 0) {
						entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 1), 0.8, (entity.getLookAngle().z * 1)));
					} else {
						entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * (-1)), 0.8, (entity.getLookAngle().z * (-1))));
					}
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
						fx = (Math.toDegrees(Math.atan(((-1) * entity.getLookAngle().x) / (entity.getLookAngle().z + 0.000001))) + 180 * (Math.signum(entity.getLookAngle().z) / 2)) - 0;
						for (int index5 = 0; index5 < 12; index5++) {
							fx = fx + 30;
							if (world instanceof ServerLevel projectileLevel) {
								Projectile _entityToSpawn = new Object() {
									public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
										AbstractArrow entityToSpawn = new AberrantMeteoriteCreeperProjectileEntity(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), level);
										entityToSpawn.setOwner(shooter);
										entityToSpawn.setBaseDamage(damage);
										entityToSpawn.setKnockback(knockback);
										entityToSpawn.setSilent(true);
										return entityToSpawn;
									}
								}.getArrow(projectileLevel, entity, 0, 0);
								_entityToSpawn.setPos(x, y, z);
								_entityToSpawn.shoot(Math.cos((fx * 3.1415) / 180), (Mth.nextDouble(RandomSource.create(), 0.5, 0.7)), Math.sin((fx * 3.1415) / 180), (float) 0.6, 0);
								projectileLevel.addFreshEntity(_entityToSpawn);
							}
							{
								final Vec3 _center = new Vec3(x, y, z);
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (entityiterator instanceof AberrantMeteoriteCreeperProjectileEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
										entityiterator.getPersistentData().putDouble("born", 123);
										entityiterator.getPersistentData().putDouble("age", 102);
										entityiterator.getPersistentData().putDouble("damage", (0.75
												* (entity instanceof LivingEntity _livingEntity414 && _livingEntity414.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity414.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
									}
								}
							}
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.HOSTILE, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")), SoundSource.HOSTILE, 1, 1, false);
							}
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 20 && entity.onGround()) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, true);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackType, 6);
					if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_CanJump, 100);
				}
			}
		}
		if (entity instanceof AberrantMeteoriteCreeperEntity _datEntL425 && _datEntL425.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
				&& (entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) : 0) == 6) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().stop();
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) == 1) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
					BlockBreakParticleSpawn2Procedure.execute(world, x, y - 0.8, z, 3.8, 40, 0.2, 0.1);
					fx = (Math.toDegrees(Math.atan(((-1) * entity.getLookAngle().x) / (entity.getLookAngle().z + 0.000001))) + 180 * (Math.signum(entity.getLookAngle().z) / 2)) - 110;
					for (int index6 = 0; index6 < 18; index6++) {
						fx = fx + 20;
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = new Object() {
								public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
									AbstractArrow entityToSpawn = new ShockWaveEntity(StellarExplorerModEntities.SHOCK_WAVE.get(), level);
									entityToSpawn.setOwner(shooter);
									entityToSpawn.setBaseDamage(damage);
									entityToSpawn.setKnockback(knockback);
									entityToSpawn.setSilent(true);
									return entityToSpawn;
								}
							}.getArrow(projectileLevel, entity, 5, 1);
							_entityToSpawn.setPos(x, y, z);
							_entityToSpawn.shoot(0, 0, 0, 0, 0);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
						{
							final Vec3 _center = new Vec3(x, y, z);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof ShockWaveEntity && entityiterator.getPersistentData().getDouble("born") != 123) {
									entityiterator.getPersistentData().putDouble("born", 123);
									entityiterator.getPersistentData().putDouble("age", 38);
									entityiterator.getPersistentData().putDouble("damage", (0.75
											* (entity instanceof LivingEntity _livingEntity439 && _livingEntity439.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity439.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)));
									entityiterator.getPersistentData().putDouble("X", (0.31 * Math.cos((fx * 3.1415) / 180)));
									entityiterator.getPersistentData().putDouble("Z", (0.31 * Math.sin((fx * 3.1415) / 180)));
								}
							}
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					for (Entity entityiterator : world.getEntities(entity, new AABB((x - 12), (y - 1), (z - 12), (x + 12), (y + 3), (z + 12)))) {
						if (entityiterator instanceof Mob || entityiterator instanceof Player) {
							if (3.6 + entityiterator.getBbWidth() / 2 > Math.sqrt(Math.pow(entityiterator.getX() - x, 2) + Math.pow(entityiterator.getY() - y, 2) + Math.pow(entityiterator.getZ() - z, 2))) {
								entityiterator.hurt(
										new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("stellar_explorer:groundshock"))), entity),
										(float) (entity instanceof LivingEntity _livingEntity451 && _livingEntity451.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity451.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0));
								if ((entityiterator instanceof LivingEntity _livingEntity454 && _livingEntity454.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
										? _livingEntity454.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
										: 0) < 1) {
									entityiterator.push(0,
											(0.6 * (1 - (entityiterator instanceof LivingEntity _livingEntity455 && _livingEntity455.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE)
													? _livingEntity455.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()
													: 0))),
											0);
								}
							}
						}
					}
				}
			}
			if ((entity instanceof AberrantMeteoriteCreeperEntity _datEntI ? _datEntI.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackTick) : 0) > 15) {
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_AttackTick, 0);
				if (entity instanceof AberrantMeteoriteCreeperEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AberrantMeteoriteCreeperEntity.DATA_InAttack, false);
			}
		}
	}
}
