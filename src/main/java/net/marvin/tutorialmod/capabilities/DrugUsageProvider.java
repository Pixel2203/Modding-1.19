package net.marvin.tutorialmod.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrugUsageProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<DrugUsage> DRUG_USAGE = CapabilityManager.get(new CapabilityToken<DrugUsage>() {

    });
    private final LazyOptional<DrugUsage> optional = LazyOptional.of(this::createPlayerDrugUsage);
    private DrugUsage drugUsage = null;
    private DrugUsage createPlayerDrugUsage(){
        if(this.drugUsage==null){
            this.drugUsage = new DrugUsage();
        }
        return this.drugUsage;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap ==DRUG_USAGE){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerDrugUsage().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerDrugUsage().loadNBTData(nbt);
    }
}
