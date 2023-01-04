
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.changedmod.entity.WhiteGooBlobEntity;
import net.mcreator.changedmod.entity.TigerSharkEntity;
import net.mcreator.changedmod.entity.PuroEntity;
import net.mcreator.changedmod.ChangedMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChangedMod.MODID);
	public static final RegistryObject<EntityType<PuroEntity>> PURO = register("puro",
			EntityType.Builder.<PuroEntity>of(PuroEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(PuroEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<TigerSharkEntity>> TIGER_SHARK = register("tiger_shark",
			EntityType.Builder.<TigerSharkEntity>of(TigerSharkEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TigerSharkEntity::new).fireImmune()
					.sized(0.6f, 1.7999999999999998f));
	public static final RegistryObject<EntityType<WhiteGooBlobEntity>> WHITE_GOO_BLOB = register("white_goo_blob",
			EntityType.Builder.<WhiteGooBlobEntity>of(WhiteGooBlobEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(WhiteGooBlobEntity::new)

					.sized(0.6f, 0.4f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PuroEntity.init();
			TigerSharkEntity.init();
			WhiteGooBlobEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PURO.get(), PuroEntity.createAttributes().build());
		event.put(TIGER_SHARK.get(), TigerSharkEntity.createAttributes().build());
		event.put(WHITE_GOO_BLOB.get(), WhiteGooBlobEntity.createAttributes().build());
	}
}
