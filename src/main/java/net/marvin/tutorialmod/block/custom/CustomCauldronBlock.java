package net.marvin.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CustomCauldronBlock extends CauldronBlock {
    public static final IntegerProperty WATER_LEVEL = IntegerProperty.create("waterlevel",0,3);

    public CustomCauldronBlock(Properties properties) {
        super(properties);
        this.stateDefinition.any().setValue(WATER_LEVEL,0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATER_LEVEL);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            ItemStack item = player.getItemInHand(hand);
            if(item.getItem() == Items.WATER_BUCKET){
                player.setItemInHand(hand,new ItemStack(Items.BUCKET));
                level.setBlock(blockPos,blockState.setValue(WATER_LEVEL,blockState.getValue(WATER_LEVEL)+1),3);
            }
        }
        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }
}
