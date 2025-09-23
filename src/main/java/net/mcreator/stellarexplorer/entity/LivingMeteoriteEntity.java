
package net.mcreator.stellarexplorer.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.procedures.LivingMeteoriteTickProcedure;
import net.mcreator.stellarexplorer.procedures.LivingMeteoriteRandomWalkProcedure;
import net.mcreator.stellarexplorer.procedures.LivingMeteoriteAnimationAttack2Procedure;
import net.mcreator.stellarexplorer.procedures.LivingMeteoriteAnimationAttack1Procedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperAnimationWalkProcedure;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;

public class LivingMeteoriteEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_Pathfinding = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_HasTarget = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_InAttack = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_AttackTick = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AttackType = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_CanRange = SynchedEntityData.defineId(LivingMeteoriteEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState1 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();

	public LivingMeteoriteEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(StellarExplorerModEntities.LIVING_METEORITE.get(), world);
	}

	public LivingMeteoriteEntity(EntityType<LivingMeteoriteEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 5;
		setNoAi(false);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
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
		this.entityData.define(DATA_AttackType, 0);
		this.entityData.define(DATA_CanRange, 0);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = LivingMeteoriteEntity.this.getRandom();
				double dir_x = LivingMeteoriteEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = LivingMeteoriteEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = LivingMeteoriteEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}

			@Override
			public boolean canUse() {
				double x = LivingMeteoriteEntity.this.getX();
				double y = LivingMeteoriteEntity.this.getY();
				double z = LivingMeteoriteEntity.this.getZ();
				Entity entity = LivingMeteoriteEntity.this;
				Level world = LivingMeteoriteEntity.this.level();
				return super.canUse() && AberrantMeteoriteCreeperAnimationWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = LivingMeteoriteEntity.this.getX();
				double y = LivingMeteoriteEntity.this.getY();
				double z = LivingMeteoriteEntity.this.getZ();
				Entity entity = LivingMeteoriteEntity.this;
				Level world = LivingMeteoriteEntity.this.level();
				return super.canContinueToUse() && AberrantMeteoriteCreeperAnimationWalkProcedure.execute(entity);
			}

		});
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				double x = LivingMeteoriteEntity.this.getX();
				double y = LivingMeteoriteEntity.this.getY();
				double z = LivingMeteoriteEntity.this.getZ();
				Entity entity = LivingMeteoriteEntity.this;
				Level world = LivingMeteoriteEntity.this.level();
				return super.canUse() && LivingMeteoriteRandomWalkProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = LivingMeteoriteEntity.this.getX();
				double y = LivingMeteoriteEntity.this.getY();
				double z = LivingMeteoriteEntity.this.getZ();
				Entity entity = LivingMeteoriteEntity.this;
				Level world = LivingMeteoriteEntity.this.level();
				return super.canContinueToUse() && LivingMeteoriteRandomWalkProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(5, new FloatGoal(this));
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
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.basalt.hit"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.basalt.break"));
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
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
		compound.putInt("DataAttackType", this.entityData.get(DATA_AttackType));
		compound.putInt("DataCanRange", this.entityData.get(DATA_CanRange));
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
		if (compound.contains("DataAttackType"))
			this.entityData.set(DATA_AttackType, compound.getInt("DataAttackType"));
		if (compound.contains("DataCanRange"))
			this.entityData.set(DATA_CanRange, compound.getInt("DataCanRange"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
			this.animationState1.animateWhen(LivingMeteoriteAnimationAttack1Procedure.execute(this), this.tickCount);
			this.animationState2.animateWhen(LivingMeteoriteAnimationAttack2Procedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LivingMeteoriteTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 28);
		builder = builder.add(Attributes.ARMOR, 4);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 7);
		builder = builder.add(Attributes.FOLLOW_RANGE, 24);
		builder = builder.add(Attributes.FLYING_SPEED, 0.3);
		return builder;
	}
}
