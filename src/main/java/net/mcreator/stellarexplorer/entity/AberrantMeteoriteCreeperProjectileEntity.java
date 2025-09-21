
package net.mcreator.stellarexplorer.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperProjectileTickProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperProjectileHitEntityProcedure;
import net.mcreator.stellarexplorer.procedures.AberrantMeteoriteCreeperProjectileHitBlockProcedure;
import net.mcreator.stellarexplorer.init.StellarExplorerModItems;
import net.mcreator.stellarexplorer.init.StellarExplorerModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class AberrantMeteoriteCreeperProjectileEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(StellarExplorerModItems.EMPTY_ITEM.get());

	public AberrantMeteoriteCreeperProjectileEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), world);
	}

	public AberrantMeteoriteCreeperProjectileEntity(EntityType<? extends AberrantMeteoriteCreeperProjectileEntity> type, Level world) {
		super(type, world);
	}

	public AberrantMeteoriteCreeperProjectileEntity(EntityType<? extends AberrantMeteoriteCreeperProjectileEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public AberrantMeteoriteCreeperProjectileEntity(EntityType<? extends AberrantMeteoriteCreeperProjectileEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		AberrantMeteoriteCreeperProjectileHitEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity(), this, this.getOwner());
		this.discard();
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		AberrantMeteoriteCreeperProjectileHitBlockProcedure.execute(this.level(), blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), this);
	}

	@Override
	public void tick() {
		super.tick();
		AberrantMeteoriteCreeperProjectileTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		if (this.inGround)
			this.discard();
	}

	public static AberrantMeteoriteCreeperProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 0f, 0, 0);
	}

	public static AberrantMeteoriteCreeperProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 0f, 0, 0);
	}

	public static AberrantMeteoriteCreeperProjectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		AberrantMeteoriteCreeperProjectileEntity entityarrow = new AberrantMeteoriteCreeperProjectileEntity(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static AberrantMeteoriteCreeperProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
		AberrantMeteoriteCreeperProjectileEntity entityarrow = new AberrantMeteoriteCreeperProjectileEntity(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(0);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
