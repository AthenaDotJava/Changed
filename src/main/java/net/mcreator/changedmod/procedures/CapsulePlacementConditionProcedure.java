package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CapsulePlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.getBlockState(new BlockPos(x, y - 1, z)).canOcclude()) {
			return true;
		} else if (world.getBlockState(new BlockPos(x, y - 2, z)).canOcclude()) {
			return true;
		}
		return false;
	}
}
