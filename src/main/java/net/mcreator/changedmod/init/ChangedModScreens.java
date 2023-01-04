
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.changedmod.client.gui.TerminalUIScreen;
import net.mcreator.changedmod.client.gui.SaveStationUIScreen;
import net.mcreator.changedmod.client.gui.CapsuleSleepUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChangedModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(ChangedModMenus.SAVE_STATION_UI.get(), SaveStationUIScreen::new);
			MenuScreens.register(ChangedModMenus.CAPSULE_SLEEP_UI.get(), CapsuleSleepUIScreen::new);
			MenuScreens.register(ChangedModMenus.TERMINAL_UI.get(), TerminalUIScreen::new);
		});
	}
}
