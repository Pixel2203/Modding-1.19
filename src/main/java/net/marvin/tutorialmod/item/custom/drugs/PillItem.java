package net.marvin.tutorialmod.item.custom.drugs;

import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
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
    private final int DRUG_LEVEL;
    private static final int DEFAULT_DRUG_LEVEL = 1;
    private static final int DEFAULT_USE_COOLDOWN = 1;
    public PillItem(Properties properties, int useDuration, int useCooldown, int drugLevel) {
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;
        this.DRUG_LEVEL = drugLevel;

    }
    public PillItem(Properties properties, int useDuration, int useCooldown){
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
    }
    public PillItem(Properties properties, int useDuration){
        super(properties);
        this.USE_DURATION = useDuration;
        this.USE_COOLDOWN = DEFAULT_USE_COOLDOWN;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return this.USE_DURATION;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(interactionHand == InteractionHand.MAIN_HAND && !level.isClientSide()){
            player.getCooldowns().addCooldown(player.getItemInHand(interactionHand).getItem(), USE_COOLDOWN);
            increaseDrugLevel(player);
        }
        return super.use(level, player, interactionHand);
    }
    private void increaseDrugLevel(Player player){
        player.getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(usage -> {
            usage.addDrugLevel(this.DRUG_LEVEL);
            System.out.println("Neues Drug Level: " + usage.getDrugLevel());
        });
    }

}
