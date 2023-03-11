
package net.mcreator.changedmod.item;

import net.mcreator.changedmod.ChangedMod;
import utils.enums.CardTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

import net.mcreator.changedmod.init.ChangedModTabs;
import java.util.List;

public class KeyCardItem extends Item {
	public static final String BASE_DESCRIPTION_ID = "item." + ChangedMod.MODID + ".key_card";
	public KeyCardItem() {
		this(CardTypes.WHITE);
	}
	public KeyCardItem(CardTypes colour) {
		super(new Item.Properties().tab(ChangedModTabs.TAB_CHANGED).stacksTo(1).rarity(Rarity.RARE));
		//setColour(this.getDefaultInstance(), colour);
	}
	public ItemStack getDefaultInstance(){
		return setColour(super.getDefaultInstance(), CardTypes.WHITE);
	}
	public static ItemStack setColour(ItemStack item, CardTypes colour) {
		if (colour == CardTypes.NONE) {
			item.removeTagKey("Colour");
			item.removeTagKey("CustomModelData");
		} else {
			item.getOrCreateTag().putString("Colour", colour.getSerializedName());
			item.getOrCreateTag().putInt("CustomModelData", colour.getColourID());
		}

		return item;
	}
	public CardTypes getColour(ItemStack stack) {
		var colour = CardTypes.NONE;
		try {
			var tag = stack.getOrCreateTag().get("Colour").toString().toUpperCase();
			tag = tag.substring(1, tag.length() - 1);
			colour = CardTypes.valueOf(tag);
		} catch (Exception e) {
		}
		return colour;
	}
	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		var colour = itemstack.getOrCreateTag().get("Colour");
		if (isValidColour(colour))
			list.add(Component.literal("Used to unlock" + colour.toString().replace('"',' ') + "Terminals."));
		else
			list.add(Component.literal("This can't unlock any terminals..."));
	}
	private boolean isValidColour(ItemStack stack){
		return isValidColour(stack.getOrCreateTag().get("Colour"));
	}

	private boolean isValidColour(Tag tag) {
		String s = null;
		if (tag != null && tag.toString().length() > 1)
			s = tag.toString().substring(1, tag.toString().length() - 1);
		try {
			CardTypes.valueOf(s.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getDescriptionId(ItemStack stack){
		if (!isValidColour(stack)) {
			return BASE_DESCRIPTION_ID + ".colour.none";
		}
		return BASE_DESCRIPTION_ID + ".colour." + getColour(stack).getSerializedName();
	}
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stack) {
		for(CardTypes colour : CardTypes.values()){
			if (colour != CardTypes.NONE && tab.equals(ChangedModTabs.TAB_CHANGED))
				stack.add(setColour(new ItemStack(this), colour));
		}
	}
}
