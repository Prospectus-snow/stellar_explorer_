
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.stellarexplorer.client.renderer.MeteoriteCreeperRenderer;
import net.mcreator.stellarexplorer.client.renderer.MeteorRenderer;
import net.mcreator.stellarexplorer.client.renderer.AberrantMeteoriteCreeperRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StellarExplorerModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(StellarExplorerModEntities.METEOR.get(), MeteorRenderer::new);
		event.registerEntityRenderer(StellarExplorerModEntities.METEORITE_CREEPER.get(), MeteoriteCreeperRenderer::new);
		event.registerEntityRenderer(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER.get(), AberrantMeteoriteCreeperRenderer::new);
		event.registerEntityRenderer(StellarExplorerModEntities.SHOCK_WAVE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER_PROJECTILE.get(), ThrownItemRenderer::new);
	}
}
