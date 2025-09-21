
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.stellarexplorer.client.particle.VEOutputParticle;
import net.mcreator.stellarexplorer.client.particle.BigMeteorSmokeParticle;
import net.mcreator.stellarexplorer.client.particle.AberrantMeteoriteCreeperBombParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StellarExplorerModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(StellarExplorerModParticleTypes.VE_OUTPUT.get(), VEOutputParticle::provider);
		event.registerSpriteSet(StellarExplorerModParticleTypes.BIG_METEOR_SMOKE.get(), BigMeteorSmokeParticle::provider);
		event.registerSpriteSet(StellarExplorerModParticleTypes.ABERRANT_METEORITE_CREEPER_BOMB.get(), AberrantMeteoriteCreeperBombParticle::provider);
	}
}
