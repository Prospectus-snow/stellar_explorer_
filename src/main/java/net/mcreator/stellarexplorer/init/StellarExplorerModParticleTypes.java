
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.stellarexplorer.StellarExplorerMod;

public class StellarExplorerModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, StellarExplorerMod.MODID);
	public static final RegistryObject<SimpleParticleType> VE_OUTPUT = REGISTRY.register("ve_output", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> BIG_METEOR_SMOKE = REGISTRY.register("big_meteor_smoke", () -> new SimpleParticleType(true));
}
