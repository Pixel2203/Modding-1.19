package net.marvin.tutorialmod.block.custom;

import com.google.common.collect.ImmutableMap;
import net.marvin.tutorialmod.Tutorialmod;
import net.marvin.tutorialmod.block.BlockRegistry;
import net.marvin.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fluids.FluidType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSoilBlock extends Block {

    public CustomSoilBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource source) {
        Tutorialmod.LOGGER.info(Tutorialmod.LOGGER_PREFIX + " RandomTick!");
        BlockState plantState = level.getBlockState(pos.above());

        if(plantState.getBlock() instanceof IPlantable){
            Tutorialmod.LOGGER.info(Tutorialmod.LOGGER_PREFIX + " Planze auf diesem Block entdeckt!");
            Property<Integer> ageProperty = ((CropBlock)plantState.getBlock()).getAgeProperty();
            int plantAge = plantState.getValue(ageProperty);
            int maxAge = ((CropBlock) plantState.getBlock()).getMaxAge();
            if(plantAge < maxAge){
                plantState.setValue(ageProperty, plantAge+1);
                System.out.println("Beschleunigung!");
            }



        }
        super.randomTick(blockState, level, pos, source);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        super.createBlockStateDefinition(p_49915_);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        Block plant = plantable.getPlant(world,pos).getBlock();
        if(plant == Blocks.CARROTS){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }

}
