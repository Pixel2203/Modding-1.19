package net.marvin.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;

public class CustomSoilBlock extends Block {
    public static final BooleanProperty ISFARMLAND = BooleanProperty.create("isfarmland");
    public static final int FERTILITY_MAX = 50;
    public static final IntegerProperty FERTILITY = IntegerProperty.create("fertility",0, FERTILITY_MAX);
    public static final BooleanProperty ISFERTILIZED = BooleanProperty.create("isfertilized");

    public CustomSoilBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(
                this.stateDefinition.any()
                        .setValue(ISFARMLAND, false)
                        .setValue(FERTILITY, 0)
                        .setValue(ISFERTILIZED,false)
        );

    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ISFARMLAND);
        builder.add(FERTILITY);
        builder.add(ISFERTILIZED);
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float p_152430_) {
        if(blockState.getValue(ISFARMLAND)){
            turnToDirt(blockPos,blockState,level);
        }
        super.fallOn(level, blockState, blockPos, entity, p_152430_);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource source) {
        BlockState plantState = level.getBlockState(pos.above());

        if(plantState.getBlock() instanceof IPlantable){
            growPlant(blockState,level,pos);
        }else if(blockState.getValue(FERTILITY) == 0){
            turnToDirt(pos,blockState,level);
        }
        super.randomTick(blockState,level,pos,source);
    }
    private void growPlant(BlockState blockState, ServerLevel level, BlockPos pos){
        BlockState plantState = level.getBlockState(pos.above());
        Property<Integer> ageProperty = ((CropBlock)plantState.getBlock()).getAgeProperty();
        int plantAge = plantState.getValue(ageProperty);
        int maxAge = ((CropBlock) plantState.getBlock()).getMaxAge();
        if(plantAge < maxAge){
            if(blockState.getValue(FERTILITY) > 0){
                boolean isfertilized = blockState.getValue(FERTILITY) > 1;
                level.setBlock(pos,blockState.setValue(FERTILITY,blockState.getValue(FERTILITY)-1)
                        .setValue(ISFERTILIZED, isfertilized ),3);
                level.setBlock(pos.above(),plantState.setValue(ageProperty, plantAge+1),3);
            }

        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult result) {
            ItemStack itemstack = player.getItemInHand(interactionHand);
            if(interactionHand == InteractionHand.MAIN_HAND &&
                    itemstack.getItem() instanceof HoeItem){

                if(state.getValue(ISFARMLAND)){
                    return  InteractionResult.PASS;
                }

                turnToFarmland(state,level,pos,player);
                return InteractionResult.SUCCESS;
            }
        return super.use(state,level,pos,player,interactionHand,result);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        if(!state.getValue(ISFARMLAND)){
           return false;
        }
        Block plant = plantable.getPlant(world,pos).getBlock();
        if(plant == Blocks.CARROTS || plant instanceof CropBlock){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }
    private void turnToFarmland(BlockState state, Level level, BlockPos pos, Player player){
        level.setBlock(pos,state.setValue(ISFARMLAND,true),3);
        player.playSound(SoundEvents.HOE_TILL);
    }
    private void turnToDirt(BlockPos blockPos, BlockState blockState, Level level){
        level.setBlock(
          blockPos,
          blockState.setValue(FERTILITY,0)
                    .setValue(ISFARMLAND,false),
                3
        );
    }
}
