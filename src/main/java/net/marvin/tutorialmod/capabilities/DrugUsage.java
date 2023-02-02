package net.marvin.tutorialmod.capabilities;

import net.minecraft.nbt.CompoundTag;

public class DrugUsage {
    private int DRUGS_IN_BLOOD = 0;
    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("drugs_in_blood" , DRUGS_IN_BLOOD);
    }

    public int getDrugLevel(){
        return this.DRUGS_IN_BLOOD;
    }
    public void addDrugLevel(int amount){
        this.DRUGS_IN_BLOOD += amount;
    }
    public void copyFrom(DrugUsage source){
        this.DRUGS_IN_BLOOD = source.DRUGS_IN_BLOOD;
    }
    public void subDrugLevel(int amount){
        this.DRUGS_IN_BLOOD = Math.min(this.DRUGS_IN_BLOOD - amount, 0 );
    }

    public void loadNBTData(CompoundTag nbt) {
        DRUGS_IN_BLOOD = nbt.getInt("drugs_in_blood");
    }
}
