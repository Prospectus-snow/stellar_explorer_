
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.stellarexplorer.world.inventory.VoidTankGUIMenu;
import net.mcreator.stellarexplorer.world.inventory.RedstoneExtractorGUIMenu;
import net.mcreator.stellarexplorer.world.inventory.MeteorAttractorGUIMenu;
import net.mcreator.stellarexplorer.StellarExplorerMod;

public class StellarExplorerModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, StellarExplorerMod.MODID);
	public static final RegistryObject<MenuType<RedstoneExtractorGUIMenu>> REDSTONE_EXTRACTOR_GUI = REGISTRY.register("redstone_extractor_gui", () -> IForgeMenuType.create(RedstoneExtractorGUIMenu::new));
	public static final RegistryObject<MenuType<VoidTankGUIMenu>> VOID_TANK_GUI = REGISTRY.register("void_tank_gui", () -> IForgeMenuType.create(VoidTankGUIMenu::new));
	public static final RegistryObject<MenuType<MeteorAttractorGUIMenu>> METEOR_ATTRACTOR_GUI = REGISTRY.register("meteor_attractor_gui", () -> IForgeMenuType.create(MeteorAttractorGUIMenu::new));
}
