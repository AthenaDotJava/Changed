
package net.mcreator.changedmod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.changedmod.init.ChangedModTabs;

import java.util.List;

public class PuroAndTheBlackGooItem extends RecordItem {
	public PuroAndTheBlackGooItem() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("changed:puroandtheblackgoo")),
				new Item.Properties().tab(ChangedModTabs.TAB_CHANGED).stacksTo(1).rarity(Rarity.RARE), 0);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("Track 09"));
	}
}
