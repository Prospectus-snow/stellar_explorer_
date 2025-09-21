
package net.mcreator.stellarexplorer.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;

import net.mcreator.stellarexplorer.init.StellarExplorerModItems;

public class XenithystHoeItem extends HoeItem {
	public XenithystHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 216;
			}

			public float getSpeed() {
				return 6f;
			}

			public float getAttackDamageBonus() {
				return 0f;
			}

			public int getLevel() {
				return 2;
			}

			public int getEnchantmentValue() {
				return 13;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(StellarExplorerModItems.XENITHYST_SHARD.get()));
			}
		}, 0, -1f, new Item.Properties());
	}
}
