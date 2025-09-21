
package net.mcreator.stellarexplorer.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;

import net.mcreator.stellarexplorer.init.StellarExplorerModItems;

public class XenithystAxeItem extends AxeItem {
	public XenithystAxeItem() {
		super(new Tier() {
			public int getUses() {
				return 216;
			}

			public float getSpeed() {
				return 6f;
			}

			public float getAttackDamageBonus() {
				return 6.5f;
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
		}, 1, -3f, new Item.Properties());
	}
}
