package net.marvin.tutorialmod.world.dimension;

import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import java.awt.*;

public class ModDimensions {
    public static final ResourceKey<Level> OVERDOSE_DIM_KEY =
            ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Tutorialmod.MOD_ID,
                    "overdosedim"));

    public static final ResourceKey<DimensionType> OVERDOSE_DIM_TYPE =
        ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, OVERDOSE_DIM_KEY.registry());

    public static void register(){

    }
}
