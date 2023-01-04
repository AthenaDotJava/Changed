
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ChangedModTabs {
	public static CreativeModeTab TAB_CHANGED;

	public static void load() {
		TAB_CHANGED = new CreativeModeTab("tabchanged") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(ChangedModBlocks.SAVE_STATION.get());
			}

			@Override
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
