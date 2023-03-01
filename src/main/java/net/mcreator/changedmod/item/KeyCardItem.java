
package net.mcreator.changedmod.item;

import net.mcreator.changedmod.ChangedMod;
import net.mcreator.changedmod.init.ChangedModItems;
import net.mcreator.changedmod.utils.CardTypes;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.mcreator.changedmod.init.ChangedModTabs;
import java.util.List;

public class KeyCardItem extends Item {
	public KeyCardItem() {
		this(CardTypes.WHITE);
	}
	public KeyCardItem(CardTypes colour) {
		super(new Item.Properties().tab(ChangedModTabs.TAB_CHANGED).stacksTo(1).rarity(Rarity.RARE));
	}
	public ItemStack getDefaultInstance(){
		return setColour(super.getDefaultInstance(), CardTypes.WHITE);
	}
	public static ItemStack setColour(ItemStack item, CardTypes colour) {
		ResourceLocation resourcelocation = ChangedModItems.KEY_CARD.getKey().location(); // newResourceLocation(ChangedMod.MODID, ChangedModItems.KEY_CARD.getId().toString());
		if (colour == CardTypes.NONE) {
			item.removeTagKey("Colour");
		} else {
			item.getOrCreateTag().putString("Colour", resourcelocation.toString());
		}

		return item;
	}
	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("Used to unlock Terminals with a matching colour."));
	}
}
