
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.changedmod.block.VentBlock;
import net.mcreator.changedmod.block.TerminalBlock;
import net.mcreator.changedmod.block.SaveStationBlock;
import net.mcreator.changedmod.block.DrainBlock;
import net.mcreator.changedmod.block.CautionFloorBlock;
import net.mcreator.changedmod.block.CapsuleBlock;
import net.mcreator.changedmod.block.BarBlock;
import net.mcreator.changedmod.ChangedMod;

public class ChangedModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ChangedMod.MODID);
	public static final RegistryObject<Block> SAVE_STATION = REGISTRY.register("save_station", () -> new SaveStationBlock());
	public static final RegistryObject<Block> DRAIN = REGISTRY.register("drain", () -> new DrainBlock());
	public static final RegistryObject<Block> VENT = REGISTRY.register("vent", () -> new VentBlock());
	public static final RegistryObject<Block> CAUTION_FLOOR = REGISTRY.register("caution_floor", () -> new CautionFloorBlock());
	public static final RegistryObject<Block> BAR = REGISTRY.register("bar", () -> new BarBlock());
	public static final RegistryObject<Block> CAPSULE = REGISTRY.register("capsule", () -> new CapsuleBlock());
	public static final RegistryObject<Block> TERMINAL = REGISTRY.register("terminal", () -> new TerminalBlock());
}
