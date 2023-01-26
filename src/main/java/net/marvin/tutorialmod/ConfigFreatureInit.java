package net.marvin.tutorialmod;


import com.google.common.base.Suppliers;
import net.marvin.tutorialmod.block.BlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

;import java.util.List;
import java.util.function.Supplier;

public class ConfigFreatureInit {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Tutorialmod.MOD_ID);
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_REPLACEMENT =
            Suppliers.memoize(() ->
                List.of(
                        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.ZIRCON_ORE.get().defaultBlockState()),
                        OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.DEEPSLATE_ZIRCON_ORE.get().defaultBlockState())
                )
            );
    public static final RegistryObject<ConfiguredFeature<?,?>> OVERWORLD_ORE =
            CONFIGURED_FEATURES.register("tutorialmod_overworld_ore", () ->
                    new ConfiguredFeature<>(Feature.ORE,
                            new OreConfiguration(OVERWORLD_REPLACEMENT.get(),11)));

}
