
package net.mcreator.changedmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.changedmod.world.inventory.SaveStationUIMenu;
import net.mcreator.changedmod.network.SaveStationUIButtonMessage;
import net.mcreator.changedmod.ChangedMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class SaveStationUIScreen extends AbstractContainerScreen<SaveStationUIMenu> {
	private final static HashMap<String, Object> guistate = SaveStationUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox StationID;
	EditBox StationPOS;

	public SaveStationUIScreen(SaveStationUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 160;
		this.imageHeight = 160;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = new ResourceLocation("changed:textures/screens/save_station_ui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		StationID.render(ms, mouseX, mouseY, partialTicks);
		StationPOS.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("changed:textures/screens/save_ui.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 160, 160, 160, 160);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (StationID.isFocused())
			return StationID.keyPressed(key, b, c);
		if (StationPOS.isFocused())
			return StationPOS.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		StationID.tick();
		StationPOS.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "Try to stay hopeful.", 26, 13, -1);
		this.font.draw(poseStack, "Station ID", -130, -9, -12779521);
		this.font.draw(poseStack, "X Y Z", -129, 24, -1);
		this.font.draw(poseStack, "Debug", -81, -22, -12844801);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.leftPos + 53, this.topPos + 70, 46, 20, Component.literal("Save"), e -> {
		}));
		StationID = new EditBox(this.font, this.leftPos + -131, this.topPos + 2, 120, 20, Component.literal(""));
		guistate.put("text:StationID", StationID);
		StationID.setMaxLength(32767);
		this.addWidget(this.StationID);
		StationPOS = new EditBox(this.font, this.leftPos + -131, this.topPos + 38, 120, 20, Component.literal(""));
		guistate.put("text:StationPOS", StationPOS);
		StationPOS.setMaxLength(32767);
		this.addWidget(this.StationPOS);
		this.addRenderableWidget(new Button(this.leftPos + -110, this.topPos + 69, 82, 20, Component.literal("Set Station"), e -> {
			if (true) {
				ChangedMod.PACKET_HANDLER.sendToServer(new SaveStationUIButtonMessage(1, x, y, z));
				SaveStationUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
