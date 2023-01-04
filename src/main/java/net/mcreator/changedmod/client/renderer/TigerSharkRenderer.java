
package net.mcreator.changedmod.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.changedmod.entity.TigerSharkEntity;
import net.mcreator.changedmod.client.model.Modelcustom_model;

public class TigerSharkRenderer extends MobRenderer<TigerSharkEntity, Modelcustom_model<TigerSharkEntity>> {
	public TigerSharkRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcustom_model(context.bakeLayer(Modelcustom_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(TigerSharkEntity entity) {
		return new ResourceLocation("changed:textures/entities/tigershark.png");
	}
}
