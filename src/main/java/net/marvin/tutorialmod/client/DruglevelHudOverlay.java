package net.marvin.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jline.utils.Colors;

public class DruglevelHudOverlay {
    private static final ResourceLocation EMPTY_DRUGLEVEL = new ResourceLocation(Tutorialmod.MOD_ID, "textures/druglevel/empty.png");
    private static final ResourceLocation FULL_DRUGLEVEL = new ResourceLocation(Tutorialmod.MOD_ID, "textures/druglevel/full.png");
    private static final ResourceLocation BORDER_DRUGLEVEL = new ResourceLocation(Tutorialmod.MOD_ID, "textures/druglevel/hud_border.png");
    public static final IGuiOverlay HUD_DRUGLEVEL =((gui, poseStack, partialTick, width, height) -> {

        drawHUD(poseStack,width,height);

    });

    private static void drawHUD(PoseStack poseStack, int width, int height) {
        int x = width/2;
        int y = height;
        int marginLeft= -94+3;
        int marginTop= -54+20-5;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,BORDER_DRUGLEVEL);
        GuiComponent.blit(poseStack,x+marginLeft,y+marginTop,0,0,80,10,80,10);

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.setShaderColor(1f, 1f, 0f, 1f); // Gelb
        GuiComponent.fill(poseStack, x, y-30, 50, 10, 1);


//        RenderSystem.setShader(null);
//        GuiComponent.fill(
//                poseStack,
//                x,
//                y,
//                80,
//                10,
//                0xFFFF00
//
//        );
    }
}
