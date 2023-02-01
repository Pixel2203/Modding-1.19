package API;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.Supplier;

public class CustomEffect {

    public static Supplier<MobEffectInstance> createCustomEffect(MobEffect effect, int durationinTicks, int effectPower){
        return () -> {
            return new MobEffectInstance(effect,durationinTicks, Math.max(effectPower-1, 1)); // Math.max so effectPower cant be negative or 0
        };
    }
}
