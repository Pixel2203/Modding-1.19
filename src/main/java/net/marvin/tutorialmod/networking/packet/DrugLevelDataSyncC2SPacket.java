package net.marvin.tutorialmod.networking.packet;

import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
import net.marvin.tutorialmod.client.ClientDrugLevelData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrugLevelDataSyncC2SPacket {
    private final int drugLevel;
    public DrugLevelDataSyncC2SPacket(int drugLevel){
        this.drugLevel = drugLevel;
    }
    public DrugLevelDataSyncC2SPacket(FriendlyByteBuf buf){
        this.drugLevel = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(drugLevel);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientDrugLevelData.set(drugLevel);
        });
        return true;
    }
}
