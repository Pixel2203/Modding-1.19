package net.marvin.tutorialmod.events;

import net.marvin.tutorialmod.Tutorialmod;
import net.marvin.tutorialmod.capabilities.DrugUsage;
import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(DrugUsageProvider.DRUG_USAGE).isPresent()){
                event.addCapability(
                        new ResourceLocation(Tutorialmod.MOD_ID,"properties"),
                        new DrugUsageProvider()
                );
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getOriginal().getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(DrugUsage.class);
    }
}
