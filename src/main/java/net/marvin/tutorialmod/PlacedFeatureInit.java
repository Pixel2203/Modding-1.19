package net.marvin.tutorialmod;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatureInit {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Tutorialmod.MOD_ID);

    public static final RegistryObject<PlacedFeature> EXAMPLE_OVERWORLD_ORE = PLACED_FEATURES.register("tutorialmod_overworld_ore",
            ()-> new PlacedFeature(ConfigFreatureInit.OVERWORLD_ORE.getHolder().get(),
                    commonOrePlacement(7,HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(40)
                    ))));
    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height){
        return orePlacement(CountPlacement.of(countPerChunk),height);
    }
    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height){
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }
}
