package net.marvin.tutorialmod.item.custom.tools;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;


public class ZirconHoeItem extends HoeItem {
    public static final BooleanProperty isFarmland = BooleanProperty.create("isfarmland");
    public ZirconHoeItem(Tier tier, int p_41337_, float p_41338_, Properties properties) {
        super(tier, p_41337_, p_41338_, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState toolModifiedState = context.getLevel().getBlockState(context.getClickedPos())
                .getToolModifiedState(context, net.minecraftforge.common.ToolActions.HOE_TILL, false);
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair =
                toolModifiedState == null ? null :
                        Pair.of(ctx -> true, changeIntoState(toolModifiedState));
        if (pair == null) {
            return InteractionResult.PASS;
        }
        return super.useOn(context);
    }
    public static Consumer<UseOnContext> changeIntoState(BlockState state) {
        return (context) -> {
            context.getLevel().setBlock(context.getClickedPos(), state, 11);
            context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), state));
        };
    }
}
