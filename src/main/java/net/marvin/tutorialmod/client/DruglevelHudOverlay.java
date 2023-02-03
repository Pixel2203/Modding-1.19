package net.marvin.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class DruglevelHudOverlay {
    private static final ResourceLocation EMPTY_DRUGLEVEL = new ResourceLocation(Tutorialmod.MOD_ID, "textures/druglevel/empty.png");
    private static final ResourceLocation FULL_DRUGLEVEL = new ResourceLocation(Tutorialmod.MOD_ID, "textures/druglevel/full.png");
    public static final IGuiOverlay HUD_DRUGLEVEL =(gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        int y = height;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,EMPTY_DRUGLEVEL);
        for(int i = 0; i < 10; i++){
            GuiComponent.blit(poseStack,x-94+(i*9),y-54,0,0,12,12,12,12);
        }
    };
}
