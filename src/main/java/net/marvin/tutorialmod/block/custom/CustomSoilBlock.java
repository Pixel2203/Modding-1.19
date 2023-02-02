package net.marvin.tutorialmod.block.custom;

import net.marvin.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
public class CustomSoilBlock extends Block {

    public CustomSoilBlock(Properties p_49795_) { super(p_49795_); }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult result) {
            ItemStack itemstack = player.getItemInHand(interactionHand);
            if(interactionHand == InteractionHand.MAIN_HAND &&
                    !level.isClientSide() &&
                    itemstack.getItem() instanceof HoeItem){
                turnToFarmland(level,pos,player);
                return InteractionResult.SUCCESS;
            }
        return super.use(state,level,pos,player,interactionHand,result);
    }


    private void turnToFarmland(Level level, BlockPos pos, Player player){
        level.setBlock(pos, ModBlocks.CUSTOM_FARMLAND_BLK.defaultBlockState(), 3);
        player.playSound(SoundEvents.HOE_TILL);
    }

}
