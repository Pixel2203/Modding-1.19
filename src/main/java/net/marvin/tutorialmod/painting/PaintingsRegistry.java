package net.marvin.tutorialmod.painting;

import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PaintingsRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Tutorialmod.MOD_ID);

    public static final RegistryObject<PaintingVariant> FOX =
            PAINTING_VARIANTS.register("fox",
                    () -> ModPaintings.FOX_PV);
    public static void register(IEventBus bus){
        PAINTING_VARIANTS.register(bus);
    }
}
