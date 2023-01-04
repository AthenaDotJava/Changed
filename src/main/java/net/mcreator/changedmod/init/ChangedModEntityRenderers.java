
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.changedmod.client.renderer.WhiteGooBlobRenderer;
import net.mcreator.changedmod.client.renderer.TigerSharkRenderer;
import net.mcreator.changedmod.client.renderer.PuroRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChangedModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ChangedModEntities.PURO.get(), PuroRenderer::new);
		event.registerEntityRenderer(ChangedModEntities.TIGER_SHARK.get(), TigerSharkRenderer::new);
		event.registerEntityRenderer(ChangedModEntities.WHITE_GOO_BLOB.get(), WhiteGooBlobRenderer::new);
	}
}
