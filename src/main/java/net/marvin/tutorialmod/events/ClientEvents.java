package net.marvin.tutorialmod.events;

import net.marvin.tutorialmod.Tutorialmod;
import net.marvin.tutorialmod.client.DruglevelHudOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("druglevel", DruglevelHudOverlay.HUD_DRUGLEVEL);
        }
    }
}
