package net.marvin.tutorialmod.item.custom.drugs;

import net.marvin.tutorialmod.capabilities.DrugUsage;
import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
import net.marvin.tutorialmod.networking.ModMessages;
import net.marvin.tutorialmod.networking.packet.ConsumeDrugC2SPacket;
import net.marvin.tutorialmod.networking.packet.ExampleC2SPacket;
import net.marvin.tutorialmod.world.dimension.ModDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
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
            ModMessages.sendToServer(new ConsumeDrugC2SPacket()); // Synchronisierung des Drug Level über Server
        }
        return super.use(level, player, interactionHand);
    }
    public void increaseDrugLevel(Player player){
        player.getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(usage -> {
            usage.addDrugLevel(this.DRUG_LEVEL);
            System.out.println("Neues Drug Level: " + usage.getDrugLevel());
            if(usage.getDrugLevel() > DrugUsage.maxDrugLevel){
                sendToOverdoseDimension(player,player.getLevel());
            }
        });

    }
    private void sendToOverdoseDimension(Player player, Level level){
        if(player.getLevel().dimensionTypeId() != ModDimensions.OVERDOSE_DIM_TYPE){
            MinecraftServer minecraftServer = level.getServer();
            ServerLevel destinationWorld = minecraftServer.getLevel(ModDimensions.OVERDOSE_DIM_KEY);
            if(destinationWorld != null){
                System.out.println("Willkommen in der DrugDimension");
                player.changeDimension(destinationWorld, new ITeleporter() {
                    @Override
                    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                        return repositionEntity.apply(false);
                    }
                });
            }

        }else{
            System.out.println("Bereits in der DrugDimension");
        }
    }
    private void consumeItem(Player player, InteractionHand interactionHand){
        ItemStack pills = player.getItemInHand(interactionHand);
        pills.shrink(1);
        player.setItemInHand(interactionHand,pills);
        player.playSound(SoundEvents.GENERIC_EAT);
        this.EFFECT_LIST.stream()
                        .filter(pair -> pair.getB() >= 1 || new Random().nextFloat(0,1) > (1-pair.getB()))
                        .map(Pair::getA)
                        .map(Supplier::get)
                        .forEach(player::addEffect);




    }

}
