package net.marvin.tutorialmod.block;


import net.marvin.tutorialmod.block.custom.BlueBerryCrop;
import net.marvin.tutorialmod.block.custom.CustomSoilBlock;
import net.marvin.tutorialmod.block.custom.FishHook;
import net.minecraft.util.valueproviders.UniformInt;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModBlocks {


    public static final Block ZIRCON_BLOCK_BLK =
            new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops());

    public static final DropExperienceBlock ZIRCON_ORE_BLK =
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7));
    public static final DropExperienceBlock DEEPSLATE_ZIRCON_ORE_BLK =
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7));

    // Custom
    public static final Block FISHHOOK_BLK =
            new FishHook(BlockBehaviour.Properties.of(Material.STONE).strength(6f));
    public static final CropBlock BLUEBERRY_CROP_BLK =
            new BlueBerryCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT));
    public static final Block CUSTOM_SOIL_BLK =
            new CustomSoilBlock(BlockBehaviour.Properties.of(Material.DIRT).randomTicks());





}
