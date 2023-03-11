package net.mcreator.changedmod.procedures;

import utils.PlayerUtils;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class PassSetAndTestProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock().getStateDefinition().getProperty("pass_set") instanceof BooleanProperty _getbp1
				&& (world.getBlockState(new BlockPos(x, y, z))).getValue(_getbp1)) {
			{
				int _value =
						Integer.parseInt(String.valueOf
								(Component.literal(
										(guistate.containsKey("text:TextInput") ?
										((EditBox) guistate.get("text:TextInput")).getValue() : ""
										)
									)
								)
						);
				BlockState _bs = PlayerUtils.lookingAt();
				BlockPos _pos = PlayerUtils.lookingAtPOS();
				if (_bs.getBlock().getStateDefinition().getProperty("pass") instanceof IntegerProperty _integerProp
						&& _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(
						Component.literal((guistate.containsKey("text:TextInput") ? ((EditBox) guistate.get("text:TextInput")).getValue() : "")),
						(false));
			if (0 == ((world.getBlockState(new BlockPos(x, y, z))).getBlock().getStateDefinition()
					.getProperty("pass") instanceof IntegerProperty _getip6 ? (world.getBlockState(new BlockPos(x, y, z))).getValue(_getip6) : -1)) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("Unlocked"), (false));
			}
		}
	}
}
