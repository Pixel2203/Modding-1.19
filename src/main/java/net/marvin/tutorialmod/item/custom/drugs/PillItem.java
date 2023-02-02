package net.marvin.tutorialmod.item.custom.drugs;

import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import java.util.Random;
import java.util.function.Supplier;

public class PillItem extends Item {
    private final int USE_DURATION;
    private final int USE_COOLDOWN;
    private final int DRUG_LEVEL;
    private final List<Pair<Supplier<MobEffectInstance>,Float>> EFFECT_LIST;
    private static final int DEFAULT_DRUG_LEVEL = 1;
    private static final int DEFAULT_USE_COOLDOWN = 25; // ticks
    private static final int DEFAULT_USE_DURATION = 1; // ticks
    public PillItem(Properties properties, List<Pair<Supplier<MobEffectInstance>,Float>> effectList, int useDuration, int useCooldown, int drugLevel) {
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;
        this.DRUG_LEVEL = drugLevel;
        this.EFFECT_LIST = effectList;
    }
    public PillItem(Properties properties, List<Pair<Supplier<MobEffectInstance>,Float>> effectList, int useDuration, int useCooldown) {
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
        this.EFFECT_LIST = effectList;
    }
    public PillItem(Properties properties, List<Pair<Supplier<MobEffectInstance>,Float>> effectList) {
        super(properties);
        this.USE_COOLDOWN = DEFAULT_USE_COOLDOWN;
        this.USE_DURATION = DEFAULT_USE_DURATION;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
        this.EFFECT_LIST = effectList;
    }
    public PillItem(Properties properties, int useDuration, int useCooldown){
        super(properties);
        this.USE_COOLDOWN = useCooldown;
        this.USE_DURATION = useDuration;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
        this.EFFECT_LIST = List.of();
    }
    public PillItem(Properties properties, int useDuration){
        super(properties);
        this.USE_DURATION = useDuration;
        this.USE_COOLDOWN = DEFAULT_USE_COOLDOWN;
        this.DRUG_LEVEL = DEFAULT_DRUG_LEVEL;
        this.EFFECT_LIST = List.of();
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
            consumeItem(player,interactionHand);
        }
        return super.use(level, player, interactionHand);
    }
    private void increaseDrugLevel(Player player){
        player.getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(usage -> {
            usage.addDrugLevel(this.DRUG_LEVEL);
            System.out.println("Neues Drug Level: " + usage.getDrugLevel());
        });
    }
    private void consumeItem(Player player, InteractionHand interactionHand){
        ItemStack pills = player.getItemInHand(interactionHand);

        for(Pair<Supplier<MobEffectInstance>,Float> pair : this.EFFECT_LIST){
            float probability = pair.getB();
            if(probability >= 1){
                player.addEffect(pair.getA().get());
            }else if(probability > 0){
                Random random = new Random();
                float success = random.nextFloat(0,1);
                if(success > 1-probability){
                    player.addEffect(pair.getA().get());
                }
            }
        }
        player.playSound(SoundEvents.GENERIC_EAT);



        pills.shrink(1);
        player.setItemInHand(interactionHand,pills);


    }

}
