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
    public static final List<Pair<Supplier<MobEffectInstance>, Float>> CAFFEINE_PILL_PROPERTIES =
                    List.of(
                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.MOVEMENT_SPEED,
                                    200,
                                    4
                                    ),
                        2f)
                    );
    public static final List<Pair<Supplier<MobEffectInstance>, Float>> PAINKILLER_PILL_PROPERTIES =
                    List.of(
                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.DAMAGE_RESISTANCE,
                                    200,
                                    2
                            ),
                            1f),

                            new Pair<>(CustomEffect.createCustomEffect(
                                    MobEffects.CONFUSION,
                                    200,
                                    1
                            ),
                                    1f),
                            new Pair<>(
                                    CustomEffect.createCustomEffect(
                                    MobEffects.WEAKNESS,
                                    200,
                                    2),0.75f
                                    )
                            );
}
