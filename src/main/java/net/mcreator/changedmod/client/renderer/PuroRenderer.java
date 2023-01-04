
package net.mcreator.changedmod.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.changedmod.entity.PuroEntity;
import net.mcreator.changedmod.client.model.Modelpuro;

public class PuroRenderer extends MobRenderer<PuroEntity, Modelpuro<PuroEntity>> {
	public PuroRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpuro(context.bakeLayer(Modelpuro.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PuroEntity entity) {
		return new ResourceLocation("changed:textures/entities/puro_texture.png");
	}
}
