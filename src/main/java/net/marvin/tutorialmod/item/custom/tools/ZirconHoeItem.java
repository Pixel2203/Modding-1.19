package net.marvin.tutorialmod.item.custom.tools;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;

public class ZirconHoeItem extends HoeItem {
    public ZirconHoeItem(Tier tier, int p_41337_, float p_41338_, Properties properties) {
        super(tier, p_41337_, p_41338_, properties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        player.getInventory().removeItem(item);
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }
}
