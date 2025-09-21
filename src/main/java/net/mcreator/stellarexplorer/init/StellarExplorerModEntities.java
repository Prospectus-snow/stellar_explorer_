
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.stellarexplorer.entity.ShockWaveEntity;
import net.mcreator.stellarexplorer.entity.MeteoriteCreeperEntity;
import net.mcreator.stellarexplorer.entity.MeteorEntity;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperProjectileEntity;
import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;
import net.mcreator.stellarexplorer.StellarExplorerMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StellarExplorerModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StellarExplorerMod.MODID);
	public static final RegistryObject<EntityType<MeteorEntity>> METEOR = register("meteor",
			EntityType.Builder.<MeteorEntity>of(MeteorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MeteorEntity::new).fireImmune().sized(3f, 3f));
	public static final RegistryObject<EntityType<MeteoriteCreeperEntity>> METEORITE_CREEPER = register("meteorite_creeper", EntityType.Builder.<MeteoriteCreeperEntity>of(MeteoriteCreeperEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MeteoriteCreeperEntity::new).fireImmune().sized(0.6f, 1.7f));
	public static final RegistryObject<EntityType<AberrantMeteoriteCreeperEntity>> ABERRANT_METEORITE_CREEPER = register("aberrant_meteorite_creeper",
			EntityType.Builder.<AberrantMeteoriteCreeperEntity>of(AberrantMeteoriteCreeperEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(AberrantMeteoriteCreeperEntity::new).fireImmune().sized(1.4f, 3.4f));
	public static final RegistryObject<EntityType<ShockWaveEntity>> SHOCK_WAVE = register("shock_wave",
			EntityType.Builder.<ShockWaveEntity>of(ShockWaveEntity::new, MobCategory.MISC).setCustomClientFactory(ShockWaveEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<AberrantMeteoriteCreeperProjectileEntity>> ABERRANT_METEORITE_CREEPER_PROJECTILE = register("aberrant_meteorite_creeper_projectile",
			EntityType.Builder.<AberrantMeteoriteCreeperProjectileEntity>of(AberrantMeteoriteCreeperProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(AberrantMeteoriteCreeperProjectileEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			MeteorEntity.init();
			MeteoriteCreeperEntity.init();
			AberrantMeteoriteCreeperEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(METEOR.get(), MeteorEntity.createAttributes().build());
		event.put(METEORITE_CREEPER.get(), MeteoriteCreeperEntity.createAttributes().build());
		event.put(ABERRANT_METEORITE_CREEPER.get(), AberrantMeteoriteCreeperEntity.createAttributes().build());
	}
}
