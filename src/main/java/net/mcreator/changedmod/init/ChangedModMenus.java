
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.changedmod.world.inventory.TerminalUIMenu;
import net.mcreator.changedmod.world.inventory.SaveStationUIMenu;
import net.mcreator.changedmod.world.inventory.CapsuleSleepUIMenu;
import net.mcreator.changedmod.ChangedMod;

public class ChangedModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ChangedMod.MODID);
	public static final RegistryObject<MenuType<SaveStationUIMenu>> SAVE_STATION_UI = REGISTRY.register("save_station_ui",
			() -> IForgeMenuType.create(SaveStationUIMenu::new));
	public static final RegistryObject<MenuType<CapsuleSleepUIMenu>> CAPSULE_SLEEP_UI = REGISTRY.register("capsule_sleep_ui",
			() -> IForgeMenuType.create(CapsuleSleepUIMenu::new));
	public static final RegistryObject<MenuType<TerminalUIMenu>> TERMINAL_UI = REGISTRY.register("terminal_ui",
			() -> IForgeMenuType.create(TerminalUIMenu::new));
}
