
package net.mcreator.stellarexplorer.entity;

import net.minecraft.world.entity.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.stellarexplorer.procedures.MeteoriteCreeperTickProcedure;
import net.mcreator.stellarexplorer.procedures.MeteoriteCreeperRandomWalkProcedure;
import net.mcreator.stellarexplorer.procedures.MeteoriteCreeperAnimationAttackProcedure;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;

public class MeteoriteCreeperEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_Pathfinding = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_HasTarget = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_InAttack = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_AttackTick = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_InExplode = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_ExplodeTick = SynchedEntityData.defineId(MeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState1 = new AnimationState();

	public MeteoriteCreeperEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(StellarExplorerModEntities.METEORITE_CREEPER.get(), world);
	}

	public MeteoriteCreeperEntity(EntityType<MeteoriteCreeperEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 5;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_Pathfinding, false);
		this.entityData.define(DATA_HasTarget, 0);
		this.entityData.define(DATA_InAttack, false);
		this.entityData.define(DATA_AttackTick, 0);
		this.entityData.define(DATA_InExplode, false);
		this.entityData.define(DATA_ExplodeTick, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1) {
			@Override
			public boolean canUse() {
				double x = MeteoriteCreeperEntity.this.getX();
				double y = MeteoriteCreeperEntity.this.getY();
				double z = MeteoriteCreeperEntity.this.getZ();
				Entity entity = MeteoriteCreeperEntity.this;
				Level world = MeteoriteCreeperEntity.this.level();
				return super.canUse() && MeteoriteCreeperRandomWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MeteoriteCreeperEntity.this.getX();
				double y = MeteoriteCreeperEntity.this.getY();
				double z = MeteoriteCreeperEntity.this.getZ();
				Entity entity = MeteoriteCreeperEntity.this;
				Level world = MeteoriteCreeperEntity.this.level();
				return super.canContinueToUse() && MeteoriteCreeperRandomWalkProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				double x = MeteoriteCreeperEntity.this.getX();
				double y = MeteoriteCreeperEntity.this.getY();
				double z = MeteoriteCreeperEntity.this.getZ();
				Entity entity = MeteoriteCreeperEntity.this;
				Level world = MeteoriteCreeperEntity.this.level();
				return super.canUse() && MeteoriteCreeperRandomWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MeteoriteCreeperEntity.this.getX();
				double y = MeteoriteCreeperEntity.this.getY();
				double z = MeteoriteCreeperEntity.this.getZ();
				Entity entity = MeteoriteCreeperEntity.this;
				Level world = MeteoriteCreeperEntity.this.level();
				return super.canContinueToUse() && MeteoriteCreeperRandomWalkProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(4, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.death"));
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("DataPathfinding", this.entityData.get(DATA_Pathfinding));
		compound.putInt("DataHasTarget", this.entityData.get(DATA_HasTarget));
		compound.putBoolean("DataInAttack", this.entityData.get(DATA_InAttack));
		compound.putInt("DataAttackTick", this.entityData.get(DATA_AttackTick));
		compound.putBoolean("DataInExplode", this.entityData.get(DATA_InExplode));
		compound.putInt("DataExplodeTick", this.entityData.get(DATA_ExplodeTick));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataPathfinding"))
			this.entityData.set(DATA_Pathfinding, compound.getBoolean("DataPathfinding"));
		if (compound.contains("DataHasTarget"))
			this.entityData.set(DATA_HasTarget, compound.getInt("DataHasTarget"));
		if (compound.contains("DataInAttack"))
			this.entityData.set(DATA_InAttack, compound.getBoolean("DataInAttack"));
		if (compound.contains("DataAttackTick"))
			this.entityData.set(DATA_AttackTick, compound.getInt("DataAttackTick"));
		if (compound.contains("DataInExplode"))
			this.entityData.set(DATA_InExplode, compound.getBoolean("DataInExplode"));
		if (compound.contains("DataExplodeTick"))
			this.entityData.set(DATA_ExplodeTick, compound.getInt("DataExplodeTick"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState1.animateWhen(MeteoriteCreeperAnimationAttackProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LivingEntity target = this.getTarget();
		if (target != null) {
			// 如果目标血量≤0或目标已死亡
			if (!target.isAlive() || target.getHealth() <= 0 || !canAttack(target)) {
				// 清除攻击目标
				this.setTarget(null);
			}
		}

		MeteoriteCreeperTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}


	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 4);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 7);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}
}
