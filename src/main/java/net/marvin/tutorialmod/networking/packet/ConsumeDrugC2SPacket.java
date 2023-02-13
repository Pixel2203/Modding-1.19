package net.marvin.tutorialmod.networking.packet;

import net.marvin.tutorialmod.capabilities.DrugUsage;
import net.marvin.tutorialmod.capabilities.DrugUsageProvider;
import net.marvin.tutorialmod.networking.ModMessages;
import net.marvin.tutorialmod.world.dimension.ModDimensions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Function;
import java.util.function.Supplier;

public class ConsumeDrugC2SPacket {
   public ConsumeDrugC2SPacket(){
    }

    public ConsumeDrugC2SPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    public void toBytes(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            player.getCapability(DrugUsageProvider.DRUG_USAGE).ifPresent(drugUsage -> {
                if(drugUsage.getDrugLevel() == DrugUsage.maxDrugLevel){
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
                ModMessages.sendToPlayer(new DrugLevelDataSyncC2SPacket(drugUsage.getDrugLevel()), player);
                System.out.println("Neues Drug Level: " + drugUsage.getDrugLevel());
            });
        });
        return true;
    }
}
