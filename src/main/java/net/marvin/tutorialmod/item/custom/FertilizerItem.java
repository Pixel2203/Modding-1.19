package net.marvin.tutorialmod.item.custom;

import net.marvin.tutorialmod.block.ModBlocks;
import net.marvin.tutorialmod.block.custom.CustomFarmlandBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FertilizerItem extends Item {
    private final int FERTILITY_INCREASE_PER_USE = 5;
    public FertilizerItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState blockState = level.getBlockState(clickedPos);
        InteractionHand interactionHand = context.getHand();

        if(context.getPlayer() != null &&
                !level.isClientSide() &&
                interactionHand == InteractionHand.MAIN_HAND &&
                blockState.getBlock() == ModBlocks.CUSTOM_FARMLAND_BLK &&
                blockState.getValue(CustomFarmlandBlock.FERTILITY) < CustomFarmlandBlock.FERTILITY_MAX){

            adjustItemStack(context.getPlayer(),context.getItemInHand(),interactionHand);
            return fertilizeBlock(clickedPos, blockState, level);
        }

        return super.useOn(context);
    }

    private void adjustItemStack(Player player, ItemStack itemInHand, InteractionHand interactionHand) {
        itemInHand.shrink(1);
        player.setItemInHand(interactionHand,itemInHand);
    }

    private InteractionResult fertilizeBlock(BlockPos blockPos, BlockState blockState, Level level) {
        if(blockState.getValue(CustomFarmlandBlock.FERTILITY) < CustomFarmlandBlock.FERTILITY_MAX){
            int currentFertility = blockState.getValue(CustomFarmlandBlock.FERTILITY);
            int updatedFertility =
                    Math.min((currentFertility + FERTILITY_INCREASE_PER_USE), CustomFarmlandBlock.FERTILITY_MAX);
            blockState = blockState.setValue(CustomFarmlandBlock.FERTILITY, updatedFertility);
            blockState = blockState.setValue(CustomFarmlandBlock.FERTILIZED,true);
            level.setBlock(
                    blockPos,
                    blockState,
                    3
                    );
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
