package net.marvin.tutorialmod.block.custom;

import com.google.common.collect.ImmutableMap;
import net.marvin.tutorialmod.block.BlockRegistry;
import net.marvin.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSoilBlock extends Block {
    public static final IntegerProperty FERTILIZER_STRENGTH = IntegerProperty.create("fertile",0,5);
    public List<Block> allowedPlants = new ArrayList<>();
    public CustomSoilBlock(Properties p_49795_) {
        super(p_49795_);
        addAllowedPlants();
        this.registerDefaultState(this.stateDefinition.any().setValue(FERTILIZER_STRENGTH,5));
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        final Block plant = plantable.getPlant(world, pos).getBlock();
        if(allowedPlants.contains(plant)){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }
    private void addAllowedPlants(){
        allowedPlants.add(Blocks.WHEAT);
        allowedPlants.add(ModBlocks.BLUEBERRY_CROP_BLK);
        allowedPlants.add(Blocks.CARROTS);
        allowedPlants.add(Blocks.POTATOES);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, RandomSource source) {
        if(level.getBlockState(blockPos.above()).getBlock() instanceof IPlantable){
            BlockState plantState = level.getBlockState(blockPos.above());
            Block plant = level.getBlockState(blockPos.above()).getBlock();
            if(plantState.getValue(FERTILIZER_STRENGTH) > 0){
                plantState.setValue(FERTILIZER_STRENGTH,plantState.getValue(FERTILIZER_STRENGTH)-1);
                System.out.println("Gewachsen, FERTILIZER_STENGTH Decreased to " + plantState.getValue(FERTILIZER_STRENGTH));
                super.randomTick(state, level, blockPos, source);
            }
            System.out.println("Kann nicht mehr wachsen Value:" + plantState.getValue(FERTILIZER_STRENGTH));
            return;
        }
        System.out.println("Keine Pflanze gefunden!");
        super.randomTick(state, level, blockPos, source);
    }

    private void fertilize(){

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FERTILIZER_STRENGTH);
    }

}
