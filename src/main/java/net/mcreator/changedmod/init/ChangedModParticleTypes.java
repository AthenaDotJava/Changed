
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.changedmod.ChangedMod;

public class ChangedModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ChangedMod.MODID);
	public static final RegistryObject<SimpleParticleType> SAVE_PARTICLE = REGISTRY.register("save_particle", () -> new SimpleParticleType(false));
}
