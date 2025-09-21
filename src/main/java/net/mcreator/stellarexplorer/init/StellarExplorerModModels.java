
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.stellarexplorer.client.model.ModelMeteoriteCreeper;
import net.mcreator.stellarexplorer.client.model.ModelMeteor;
import net.mcreator.stellarexplorer.client.model.ModelAberrantMeteoriteCreeper;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class StellarExplorerModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelMeteor.LAYER_LOCATION, ModelMeteor::createBodyLayer);
		event.registerLayerDefinition(ModelAberrantMeteoriteCreeper.LAYER_LOCATION, ModelAberrantMeteoriteCreeper::createBodyLayer);
		event.registerLayerDefinition(ModelMeteoriteCreeper.LAYER_LOCATION, ModelMeteoriteCreeper::createBodyLayer);
	}
}
