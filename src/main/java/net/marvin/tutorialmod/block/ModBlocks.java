package net.marvin.tutorialmod.block;


import net.marvin.tutorialmod.block.custom.CustomCauldronBlock;
import net.marvin.tutorialmod.block.custom.GemInfusingStationBlock;
import net.marvin.tutorialmod.block.custom.crops.BlueBerryCropBlock;
import net.marvin.tutorialmod.block.custom.CustomSoilBlock;
import net.marvin.tutorialmod.block.custom.CustomFarmlandBlock;
import net.marvin.tutorialmod.block.custom.crops.RaspberryBushBlock;
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
            new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).noOcclusion());
    public static final CropBlock BLUEBERRY_CROP_BLK =
            new BlueBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT));

    public static final Block CUSTOM_SOIL_BLK =
            new CustomSoilBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .randomTicks()
                    .strength(.5f)
                    .sound(SoundType.ROOTED_DIRT)


            );
    public static final Block CUSTOM_FARMLAND_BLK =
            new CustomFarmlandBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .randomTicks().strength(.5f).sound(SoundType.GRASS)
            );

    public static final BushBlock RASPBERRY_BUSH_BLK =
            new RaspberryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS));
    public static final Block GEM_INFUSING_STATION_BLK =
            new GemInfusingStationBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion());
    public static final CauldronBlock CUSTOM_CAULDRON_BLK =
            new CustomCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON));




}
