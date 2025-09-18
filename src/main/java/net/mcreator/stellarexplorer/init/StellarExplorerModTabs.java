
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.stellarexplorer.StellarExplorerMod;

public class StellarExplorerModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StellarExplorerMod.MODID);
	public static final RegistryObject<CreativeModeTab> STELLAR_EXPLORER = REGISTRY.register("stellar_explorer",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.stellar_explorer.stellar_explorer")).icon(() -> new ItemStack(StellarExplorerModBlocks.XENITHYST_ORE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(StellarExplorerModBlocks.XENITHYST_ORE.get().asItem());
				tabData.accept(StellarExplorerModBlocks.DEEPSLATE_XENITHYST_ORE.get().asItem());
				tabData.accept(StellarExplorerModItems.RAW_XENITHYST.get());
				tabData.accept(StellarExplorerModItems.XENITHYST_SHARD.get());
				tabData.accept(StellarExplorerModItems.LOCATION_RECORD_CARD.get());
				tabData.accept(StellarExplorerModBlocks.ENERGY_OUTPUT.get().asItem());
				tabData.accept(StellarExplorerModBlocks.VOID_TANK.get().asItem());
				tabData.accept(StellarExplorerModBlocks.REDSTONE_EXTRACTOR.get().asItem());
			}).build());
}
