package net.marvin.tutorialmod.item.custom;

import net.marvin.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MagicPaste extends Item {
    public MagicPaste(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){

        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState clickedBlockstate = context.getLevel().getBlockState(clickedPos);
        if(clickedBlockstate.getBlock() instanceof CauldronBlock){
            level.setBlock(clickedPos, ModBlocks.CUSTOM_CAULDRON_BLK.defaultBlockState(),3);

        }
        return super.useOn(context);
    }
}
