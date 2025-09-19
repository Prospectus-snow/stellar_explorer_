
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.stellarexplorer.client.gui.VoidTankGUIScreen;
import net.mcreator.stellarexplorer.client.gui.RedstoneExtractorGUIScreen;
import net.mcreator.stellarexplorer.client.gui.MeteorAttractorGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StellarExplorerModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(StellarExplorerModMenus.REDSTONE_EXTRACTOR_GUI.get(), RedstoneExtractorGUIScreen::new);
			MenuScreens.register(StellarExplorerModMenus.VOID_TANK_GUI.get(), VoidTankGUIScreen::new);
			MenuScreens.register(StellarExplorerModMenus.METEOR_ATTRACTOR_GUI.get(), MeteorAttractorGUIScreen::new);
		});
	}
}
