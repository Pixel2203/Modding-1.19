package net.marvin.tutorialmod.capabilities;

import net.marvin.tutorialmod.networking.ModMessages;
import net.marvin.tutorialmod.networking.packet.ConsumeDrugC2SPacket;
import net.marvin.tutorialmod.networking.packet.ExampleC2SPacket;
import net.minecraft.nbt.CompoundTag;

public class DrugUsage {
    private int DRUGS_IN_BLOOD = 0;
    public static final int maxDrugLevel = 20;
    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("drugs_in_blood" , DRUGS_IN_BLOOD);
    }

    public int getDrugLevel(){
        return this.DRUGS_IN_BLOOD;
    }
    public void addDrugLevel(int amount){
        this.DRUGS_IN_BLOOD = Math.min(this.DRUGS_IN_BLOOD+amount, maxDrugLevel);
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
