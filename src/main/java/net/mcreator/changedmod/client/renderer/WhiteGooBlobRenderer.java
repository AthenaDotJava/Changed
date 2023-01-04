
package net.mcreator.changedmod.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.changedmod.entity.WhiteGooBlobEntity;
import net.mcreator.changedmod.client.model.Modelwhitegooblob;

public class WhiteGooBlobRenderer extends MobRenderer<WhiteGooBlobEntity, Modelwhitegooblob<WhiteGooBlobEntity>> {
	public WhiteGooBlobRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelwhitegooblob(context.bakeLayer(Modelwhitegooblob.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(WhiteGooBlobEntity entity) {
		return new ResourceLocation("changed:textures/entities/white_goo_blob.png");
	}
}
