package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CapsuleDeleteProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (("up").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep1
				? blockstate.getValue(_getep1).toString()
				: "")) {
			world.setBlock(new BlockPos(x, y - 1, z), Blocks.AIR.defaultBlockState(), 3);
		} else if (("down").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep4
				? blockstate.getValue(_getep4).toString()
				: "")) {
			world.setBlock(new BlockPos(x, y + 1, z), Blocks.AIR.defaultBlockState(), 3);
		}
	}
}
