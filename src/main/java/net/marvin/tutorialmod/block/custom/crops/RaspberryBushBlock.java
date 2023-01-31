package net.marvin.tutorialmod.block.custom.crops;

import net.marvin.tutorialmod.item.ItemRegistry;
import net.marvin.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class RaspberryBushBlock extends BushBlock {
    public static final BooleanProperty HARVESTABLE = BooleanProperty.create("harvestable");
    private final int MAX_HARVEST_AMOUNT = 5;
    public RaspberryBushBlock(Properties p_51021_) {
        super(p_51021_);
        registerDefaultState(
                this.stateDefinition.any().setValue(HARVESTABLE,false)
        );

    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if(interactionHand == InteractionHand.MAIN_HAND &&
                                     !level.isClientSide() &&
                                        blockState.getValue(HARVESTABLE) ){
            harvestBush(blockPos,blockState,level,player);
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, hitResult);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource p_222957_) {
        if(!blockState.getValue(HARVESTABLE)){
            level.setBlock(blockPos,blockState.setValue(HARVESTABLE,true),3);
        }
        super.randomTick(blockState, level, blockPos, p_222957_);
    }

    private void harvestBush(BlockPos blockPos, BlockState blockState, Level level, Player player) {
        Random random = new Random();
        int amount = random.nextInt(1,this.MAX_HARVEST_AMOUNT);
        ItemStack raspberries = new ItemStack(ModItems.RASPBERRY_ITM,amount);
        player.addItem(raspberries);
        level.setBlock(blockPos,blockState.setValue(HARVESTABLE,false),3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HARVESTABLE);
    }
}
