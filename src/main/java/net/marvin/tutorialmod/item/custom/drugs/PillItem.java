package net.marvin.tutorialmod.item.custom.drugs;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.function.Supplier;

public class PillItem extends Item {
    private final int USE_DURATION;
    private final int USE_COOLDOWN;
    public PillItem(Properties properties, int useDuration, int useCooldown) {
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;

    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return this.USE_DURATION;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(interactionHand == InteractionHand.MAIN_HAND && !level.isClientSide()){
            player.getCooldowns().addCooldown(player.getItemInHand(interactionHand).getItem(), USE_COOLDOWN);
        }
        return super.use(level, player, interactionHand);
    }

}
