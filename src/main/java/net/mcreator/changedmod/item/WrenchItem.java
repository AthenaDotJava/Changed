
package net.mcreator.changedmod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.mcreator.changedmod.init.ChangedModTabs;

import java.util.List;

public class WrenchItem extends Item {
	public WrenchItem() {
		super(new Item.Properties().tab(ChangedModTabs.TAB_CHANGED).stacksTo(1).rarity(Rarity.RARE));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("Used to change the state of Bars"));
	}
}
