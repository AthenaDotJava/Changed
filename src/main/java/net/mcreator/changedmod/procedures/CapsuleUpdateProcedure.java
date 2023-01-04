package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.changedmod.init.ChangedModBlocks;

public class CapsuleUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (("upper").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep1
				? blockstate.getValue(_getep1).toString()
				: "")) {
			if (!((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == ChangedModBlocks.CAPSULE.get())) {
				world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		} else if (("lower").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep6
				? blockstate.getValue(_getep6).toString()
				: "")) {
			if (!((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock() == ChangedModBlocks.CAPSULE.get())) {
				world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}
