package net.marvin.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.IPlantable;

public class CustomFarmlandBlock extends Block {

    public static final int FERTILITY_MAX = 50;
    public static final BooleanProperty FERTILIZED = BooleanProperty.create("fertilized");
    public static final IntegerProperty FERTILITY = IntegerProperty.create("fertility",0, FERTILITY_MAX);
    public CustomFarmlandBlock(BlockBehaviour.Properties p_53247_) {
        super(p_53247_);
        registerDefaultState(
                this.stateDefinition.any().setValue(FERTILITY,0).setValue(FERTILIZED,false)
        );

    }

    private void turnToDirt(BlockPos blockPos, BlockState blockState, Level level){
        level.setBlock(
          blockPos,
          blockState.setValue(FERTILITY,0),
                3
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FERTILITY);
        builder.add(FERTILIZED);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource source) {

        BlockState plantState = level.getBlockState(pos.above());

        if (plantState.getBlock() instanceof IPlantable) {

            growPlant(blockState, level, pos);
        } else if (willSurvive(pos,blockState,level)) {
            turnToDirt(pos, blockState, level);
        }
        super.randomTick(blockState, level, pos, source);
    }

    private void growPlant(BlockState blockState, ServerLevel level, BlockPos pos){
        BlockState plantState = level.getBlockState(pos.above());
        Property<Integer> ageProperty = ((CropBlock) plantState.getBlock()).getAgeProperty();
        int plantAge = plantState.getValue(ageProperty);
        int maxAge = ((CropBlock) plantState.getBlock()).getMaxAge();
        if(plantAge < maxAge){
            if(blockState.getValue(FERTILITY) > 0){
                boolean fertilized = blockState.getValue(FERTILITY) > 1;
                level.setBlock(pos,blockState.setValue(FERTILITY,blockState.getValue(FERTILITY)-1)
                                                            .setValue(FERTILIZED, fertilized ),3);
                level.setBlock(pos.above(),plantState.setValue(ageProperty, plantAge+1),3);
                }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        Block plant = plantable.getPlant(world,pos).getBlock();
        if(plant == Blocks.CARROTS || plant instanceof CropBlock){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }
    private boolean willSurvive(BlockPos pos, BlockState blockState, Level level){
        return blockState.getValue(FERTILIZED);
    }

}
