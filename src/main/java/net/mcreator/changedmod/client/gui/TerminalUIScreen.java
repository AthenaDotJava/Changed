
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

import net.mcreator.changedmod.world.inventory.TerminalUIMenu;
import net.mcreator.changedmod.network.TerminalUIButtonMessage;
import net.mcreator.changedmod.ChangedMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class TerminalUIScreen extends AbstractContainerScreen<TerminalUIMenu> {
	private final static HashMap<String, Object> guistate = TerminalUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox TextInput;

	public TerminalUIScreen(TerminalUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 0;
		this.imageHeight = 166;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = new ResourceLocation("changed:textures/screens/terminal_ui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		TextInput.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("changed:textures/screens/terminalui.png"));
		this.blit(ms, this.leftPos + -100, this.topPos + 6, 0, 0, 200, 160, 200, 160);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (TextInput.isFocused())
			return TextInput.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		TextInput.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
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
		TextInput = new EditBox(this.font, this.leftPos + -58, this.topPos + 58, 120, 20, Component.literal("Pass")) {
			{
				setSuggestion("Pass");
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion("Pass");
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion("Pass");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:TextInput", TextInput);
		TextInput.setMaxLength(32767);
		this.addWidget(this.TextInput);
		this.addRenderableWidget(new Button(this.leftPos + -25, this.topPos + 117, 51, 20, Component.literal("Enter"), e -> {
			if (true) {
				ChangedMod.PACKET_HANDLER.sendToServer(new TerminalUIButtonMessage(0, x, y, z));
				TerminalUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
