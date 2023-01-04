package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.mcreator.changedmod.ChangedMod;

public class DrainGooPassThroughProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("changed:blobs")))) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 999, 1, (false), (false)));
			{
				Entity _ent = entity;
				_ent.teleportTo((entity.getX()), (entity.getY() - 0.125), (entity.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((entity.getX()), (entity.getY() - 0.125), (entity.getZ()), _ent.getYRot(), _ent.getXRot());
			}
			ChangedMod.queueServerWork(100, () -> {
				if (!entity.level.isClientSide())
					entity.discard();
			});
		}
	}
}
