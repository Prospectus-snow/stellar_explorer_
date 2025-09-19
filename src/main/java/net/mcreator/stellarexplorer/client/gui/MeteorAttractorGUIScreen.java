package net.mcreator.stellarexplorer.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.stellarexplorer.world.inventory.MeteorAttractorGUIMenu;
import net.mcreator.stellarexplorer.procedures.WorkBarOverlayProcedure;
import net.mcreator.stellarexplorer.procedures.VoidExudateBarOverlayProcedure;
import net.mcreator.stellarexplorer.procedures.RedstoneExtractorOverlayProcedure;
import net.mcreator.stellarexplorer.procedures.RedstoneExtractorGUITextProcedure;
import net.mcreator.stellarexplorer.procedures.MachineTipProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MeteorAttractorGUIScreen extends AbstractContainerScreen<MeteorAttractorGUIMenu> {
	private final static HashMap<String, Object> guistate = MeteorAttractorGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public MeteorAttractorGUIScreen(MeteorAttractorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("stellar_explorer:textures/screens/meteor_attractor_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 155 && mouseX < leftPos + 164 && mouseY > topPos + 10 && mouseY < topPos + 75)
			guiGraphics.renderTooltip(font, Component.literal(MachineTipProcedure.execute(world, x, y, z)), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("stellar_explorer:textures/screens/redstone_generator_redstone.png"), this.leftPos + 44, this.topPos + 41, Mth.clamp((int) RedstoneExtractorOverlayProcedure.execute(world, x, y, z) * 88, 0, 1320), 0, 88,
				4, 1408, 4);

		guiGraphics.blit(new ResourceLocation("stellar_explorer:textures/screens/void_exudate_bar.png"), this.leftPos + 156, this.topPos + 11, Mth.clamp((int) VoidExudateBarOverlayProcedure.execute(world, x, y, z) * 8, 0, 512), 0, 8, 64, 520, 64);

		guiGraphics.blit(new ResourceLocation("stellar_explorer:textures/screens/work_progress_bar.png"), this.leftPos + 56, this.topPos + 72, Mth.clamp((int) WorkBarOverlayProcedure.execute(world, x, y, z) * 64, 0, 4096), 0, 64, 3, 4160, 3);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,

				RedstoneExtractorGUITextProcedure.execute(world, x, y, z), 8, 6, -13226696, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
