
package net.mcreator.stellarexplorer.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperTickProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperRandomWalkProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack6Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack5Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack4Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack3Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack2Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationAttack1Procedure;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;

public class AberrantMeteoriteCreeperEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_Pathfinding = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_HasTarget = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_InAttack = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_AttackTick = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_InExplode = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_ExplodeTick = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AttackType = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_CanRange = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_CanJump = SynchedEntityData.defineId(AberrantMeteoriteCreeperEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();
	public final AnimationState animationState3 = new AnimationState();
	public final AnimationState animationState4 = new AnimationState();
	public final AnimationState animationState5 = new AnimationState();
	public final AnimationState animationState6 = new AnimationState();
	public final AnimationState animationState7 = new AnimationState();

	public AberrantMeteoriteCreeperEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER.get(), world);
	}

	public AberrantMeteoriteCreeperEntity(EntityType<AberrantMeteoriteCreeperEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 35;
		setNoAi(false);
		setPersistenceRequired();
		refreshDimensions();
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
		this.entityData.define(DATA_AttackType, 0);
		this.entityData.define(DATA_CanRange, 0);
		this.entityData.define(DATA_CanJump, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1) {
			@Override
			public boolean canUse() {
				double x = AberrantMeteoriteCreeperEntity.this.getX();
				double y = AberrantMeteoriteCreeperEntity.this.getY();
				double z = AberrantMeteoriteCreeperEntity.this.getZ();
				Entity entity = AberrantMeteoriteCreeperEntity.this;
				Level world = AberrantMeteoriteCreeperEntity.this.level();
				return super.canUse() && AberrantMeteoriteCreeperRandomWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = AberrantMeteoriteCreeperEntity.this.getX();
				double y = AberrantMeteoriteCreeperEntity.this.getY();
				double z = AberrantMeteoriteCreeperEntity.this.getZ();
				Entity entity = AberrantMeteoriteCreeperEntity.this;
				Level world = AberrantMeteoriteCreeperEntity.this.level();
				return super.canContinueToUse() && AberrantMeteoriteCreeperRandomWalkProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				double x = AberrantMeteoriteCreeperEntity.this.getX();
				double y = AberrantMeteoriteCreeperEntity.this.getY();
				double z = AberrantMeteoriteCreeperEntity.this.getZ();
				Entity entity = AberrantMeteoriteCreeperEntity.this;
				Level world = AberrantMeteoriteCreeperEntity.this.level();
				return super.canUse() && AberrantMeteoriteCreeperRandomWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = AberrantMeteoriteCreeperEntity.this.getX();
				double y = AberrantMeteoriteCreeperEntity.this.getY();
				double z = AberrantMeteoriteCreeperEntity.this.getZ();
				Entity entity = AberrantMeteoriteCreeperEntity.this;
				Level world = AberrantMeteoriteCreeperEntity.this.level();
				return super.canContinueToUse() && AberrantMeteoriteCreeperRandomWalkProcedure.execute(entity);
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
	public boolean isPushable() {
		return false;
	}

	private static final float ROTATION_SPEED = 20.0f;
	private float targetBodyYRot;
	public void updateBodyRotationForRender(float partialTicks) {

		float currentYRot = this.yBodyRot;

		float diff = Mth.wrapDegrees(targetBodyYRot - currentYRot);

		// 角度差小于这个值而且没在动就不跟着转
		final float DEAD_ZONE_THRESHOLD = 55.0f;
		boolean isMovingHorizontally = this.getDeltaMovement().horizontalDistanceSqr() > 0;

		if ((Math.abs(diff) < DEAD_ZONE_THRESHOLD) && isMovingHorizontally) {
			return;
		}

		float maxRot = ROTATION_SPEED * (partialTicks / 20.0f);

		if (Math.abs(diff) > maxRot) {
			float easingFactor = Math.min(Math.abs(diff) / 30.0f, 1.0f);
			currentYRot += Math.signum(diff) * maxRot * easingFactor;
		} else {
			currentYRot = targetBodyYRot;
		}

		this.yBodyRot = Mth.wrapDegrees(currentYRot);
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;

		if((damagesource.is(DamageTypes.GENERIC_KILL) || damagesource.is(DamageTypes.FELL_OUT_OF_WORLD))){
			return super.hurt(damagesource, amount);
		}


		Entity attacker = damagesource.getEntity();

		if (attacker instanceof LivingEntity living) {
			ItemStack mainHand = living.getMainHandItem();
			TagKey<Item> validTools = TagKey.create(Registries.ITEM,
					new ResourceLocation("stellar_explorer", "can_attack_meteor_mob_tool"));

			if (!mainHand.is(validTools)) {
				amount *= 0.6f;
				return super.hurt(damagesource, Math.min(amount, getMaxHealth()/9.375f));
			}
		} else {
			amount *= 0.6f;
			return super.hurt(damagesource, Math.min(amount, getMaxHealth()/9.375f));
		}

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
		compound.putInt("DataAttackType", this.entityData.get(DATA_AttackType));
		compound.putInt("DataCanRange", this.entityData.get(DATA_CanRange));
		compound.putInt("DataCanJump", this.entityData.get(DATA_CanJump));
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
		if (compound.contains("DataAttackType"))
			this.entityData.set(DATA_AttackType, compound.getInt("DataAttackType"));
		if (compound.contains("DataCanRange"))
			this.entityData.set(DATA_CanRange, compound.getInt("DataCanRange"));
		if (compound.contains("DataCanJump"))
			this.entityData.set(DATA_CanJump, compound.getInt("DataCanJump"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
			this.animationState2.animateWhen(AberrantMeteoriteCreeperAnimationAttack1Procedure.execute(this), this.tickCount);
			this.animationState3.animateWhen(AberrantMeteoriteCreeperAnimationAttack2Procedure.execute(this), this.tickCount);
			this.animationState4.animateWhen(AberrantMeteoriteCreeperAnimationAttack3Procedure.execute(this), this.tickCount);
			this.animationState5.animateWhen(AberrantMeteoriteCreeperAnimationAttack4Procedure.execute(this), this.tickCount);
			this.animationState6.animateWhen(AberrantMeteoriteCreeperAnimationAttack5Procedure.execute(this), this.tickCount);
			this.animationState7.animateWhen(AberrantMeteoriteCreeperAnimationAttack6Procedure.execute(this), this.tickCount);
		}

		this.targetBodyYRot = this.getYHeadRot();
	}

	@Override
	public void baseTick() {
		super.baseTick();
		AberrantMeteoriteCreeperTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return super.getDimensions(pose).scale(1.1f);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 150);
		builder = builder.add(Attributes.ARMOR, 6);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 10);
		builder = builder.add(Attributes.FOLLOW_RANGE, 30);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
		return builder;
	}
}
