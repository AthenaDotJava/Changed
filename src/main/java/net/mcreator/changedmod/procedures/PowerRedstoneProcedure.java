package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class PowerRedstoneProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (0 == ((world.getBlockState(new BlockPos(x, y, z))).getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip1
				? (world.getBlockState(new BlockPos(x, y, z))).getValue(_getip1)
				: -1)) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Message"), (false));
		}
	}
}
