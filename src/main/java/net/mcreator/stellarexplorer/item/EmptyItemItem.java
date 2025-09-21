
package net.mcreator.stellarexplorer.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EmptyItemItem extends Item {
	public EmptyItemItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
