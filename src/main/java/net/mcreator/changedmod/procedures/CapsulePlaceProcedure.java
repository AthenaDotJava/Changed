package net.mcreator.changedmod.procedures;

import net.mcreator.changedmod.block.CapsuleBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.minecraft.core.Direction;
import net.mcreator.changedmod.init.ChangedModBlocks;
import utils.enums.CapsuleStates;

public class CapsulePlaceProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction playerface = Direction.NORTH;
		world.setBlock(new BlockPos(x, y + 1, z), ChangedModBlocks.CAPSULE.get().defaultBlockState().setValue(CapsuleBlock.HALF, CapsuleStates.UP), 3);
		{
			Direction _dir = (new Object() {
				public Direction getDirection(BlockPos pos) {
					BlockState _bs = world.getBlockState(pos);
					Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (property != null && _bs.getValue(property) instanceof Direction _dir)
						return _dir;
					return Direction.NORTH;
				}
			}.getDirection(new BlockPos(x, y, z)));
			BlockPos _pos = new BlockPos(x, y + 1, z);
			BlockState _bs = world.getBlockState(_pos);
			Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
			if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
				world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
			} else {
				_property = _bs.getBlock().getStateDefinition().getProperty("axis");
				if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
					world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
			}
		}
	}
}
