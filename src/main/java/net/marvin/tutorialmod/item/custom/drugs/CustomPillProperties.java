package net.marvin.tutorialmod.item.custom.drugs;

import API.CustomEffect;
import net.marvin.tutorialmod.item.ModCreativeModeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.function.Supplier;

public class CustomPillProperties {
    public static final Item.Properties CAFFEINE_PILL_PROPERTIES =
            createPillProperties(
                    ModCreativeModeTab.TUTORIAL_TAB,
                    1,
                    1,
                    List.of(
                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.MOVEMENT_SPEED,
                                    200,
                                    4
                                    ),
                        2f))
            );
    public static final Item.Properties PAINKILLER_PILL_PROPERTIES =
            createPillProperties(
                    ModCreativeModeTab.TUTORIAL_TAB,
                    1,
                    1,
                    List.of(
                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.DAMAGE_RESISTANCE,
                                    200,
                                    2
                            ),
                            2f),

                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.CONFUSION,
                                    200,
                                    2
                            ),
                                    2f))
            );









    public static Item.Properties createPillProperties(CreativeModeTab modTab, int nutrition, int saturation, List<Pair<Supplier<MobEffectInstance>,Float>> effectList){
        return new Item.Properties()
                .tab(modTab)
                .food(createPillFoodProperties(nutrition,saturation,effectList));
    }
    private static FoodProperties createPillFoodProperties(int nutrition, int saturationMod, List<Pair<Supplier<MobEffectInstance>,Float>> effects){
        FoodProperties.Builder propertiesBuilder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturationMod)
                .alwaysEat();
        effects.forEach(effect -> propertiesBuilder.effect(effect.getA(), effect.getB()));
        return propertiesBuilder.build();


    }
}
