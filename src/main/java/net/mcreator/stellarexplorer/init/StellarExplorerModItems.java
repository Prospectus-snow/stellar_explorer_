
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.stellarexplorer.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.stellarexplorer.procedures.LocationRecordCardIsEmptyProcedure;
import net.mcreator.stellarexplorer.item.XenithystSwordItem;
import net.mcreator.stellarexplorer.item.XenithystShovelItem;
import net.mcreator.stellarexplorer.item.XenithystShardItem;
import net.mcreator.stellarexplorer.item.XenithystPickaxeItem;
import net.mcreator.stellarexplorer.item.XenithystHoeItem;
import net.mcreator.stellarexplorer.item.XenithystAxeItem;
import net.mcreator.stellarexplorer.item.RawXenithystItem;
import net.mcreator.stellarexplorer.item.LocationRecordCardItem;
import net.mcreator.stellarexplorer.item.EmptyItemItem;
import net.mcreator.stellarexplorer.StellarExplorerMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StellarExplorerModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, StellarExplorerMod.MODID);
	public static final RegistryObject<Item> XENITHYST_ORE = block(StellarExplorerModBlocks.XENITHYST_ORE);
	public static final RegistryObject<Item> DEEPSLATE_XENITHYST_ORE = block(StellarExplorerModBlocks.DEEPSLATE_XENITHYST_ORE);
	public static final RegistryObject<Item> XENITHYST_SHARD = REGISTRY.register("xenithyst_shard", () -> new XenithystShardItem());
	public static final RegistryObject<Item> RAW_XENITHYST = REGISTRY.register("raw_xenithyst", () -> new RawXenithystItem());
	public static final RegistryObject<Item> VOID_TANK = block(StellarExplorerModBlocks.VOID_TANK);
	public static final RegistryObject<Item> REDSTONE_EXTRACTOR = block(StellarExplorerModBlocks.REDSTONE_EXTRACTOR);
	public static final RegistryObject<Item> LOCATION_RECORD_CARD = REGISTRY.register("location_record_card", () -> new LocationRecordCardItem());
	public static final RegistryObject<Item> ENERGY_OUTPUT = block(StellarExplorerModBlocks.ENERGY_OUTPUT);
	public static final RegistryObject<Item> METEOR_ATTRACTOR = block(StellarExplorerModBlocks.METEOR_ATTRACTOR);
	public static final RegistryObject<Item> METEORITE_CREEPER_SPAWN_EGG = REGISTRY.register("meteorite_creeper_spawn_egg", () -> new ForgeSpawnEggItem(StellarExplorerModEntities.METEORITE_CREEPER, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> MACHINE_FRAME = block(StellarExplorerModBlocks.MACHINE_FRAME);
	public static final RegistryObject<Item> ABERRANT_METEORITE_CREEPER_SPAWN_EGG = REGISTRY.register("aberrant_meteorite_creeper_spawn_egg",
			() -> new ForgeSpawnEggItem(StellarExplorerModEntities.ABERRANT_METEORITE_CREEPER, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> EMPTY_ITEM = REGISTRY.register("empty_item", () -> new EmptyItemItem());
	public static final RegistryObject<Item> XENITHYST_PICKAXE = REGISTRY.register("xenithyst_pickaxe", () -> new XenithystPickaxeItem());
	public static final RegistryObject<Item> XENITHYST_AXE = REGISTRY.register("xenithyst_axe", () -> new XenithystAxeItem());
	public static final RegistryObject<Item> XENITHYST_SWORD = REGISTRY.register("xenithyst_sword", () -> new XenithystSwordItem());
	public static final RegistryObject<Item> XENITHYST_SHOVEL = REGISTRY.register("xenithyst_shovel", () -> new XenithystShovelItem());
	public static final RegistryObject<Item> XENITHYST_HOE = REGISTRY.register("xenithyst_hoe", () -> new XenithystHoeItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(LOCATION_RECORD_CARD.get(), new ResourceLocation("stellar_explorer:location_record_card_empty"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) LocationRecordCardIsEmptyProcedure.execute(itemStackToRender));
		});
	}
}
