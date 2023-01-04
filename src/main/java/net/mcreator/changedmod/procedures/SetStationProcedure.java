package net.mcreator.changedmod.procedures;

import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class SetStationProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		String ID = "";
		String POS = "";
		boolean StationSet = false;
		ID = guistate.containsKey("text:StationID") ? ((EditBox) guistate.get("text:StationID")).getValue() : "";
		POS = guistate.containsKey("text:StationPOS") ? ((EditBox) guistate.get("text:StationPOS")).getValue() : "";
		StationSet = true;
	}
}
