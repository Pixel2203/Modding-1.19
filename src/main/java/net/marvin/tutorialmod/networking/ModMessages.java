package net.marvin.tutorialmod.networking;

import net.marvin.tutorialmod.Tutorialmod;
import net.marvin.tutorialmod.networking.packet.ConsumeDrugC2SPacket;
import net.marvin.tutorialmod.networking.packet.DrugLevelDataSyncC2SPacket;
import net.marvin.tutorialmod.networking.packet.ExampleC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.http.impl.execchain.TunnelRefusedException;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static  int packetId = 0;
    private static int id(){
        return packetId++;
    }
    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Tutorialmod.MOD_ID,"messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        /*
            Es gibt 2 Messages, ConsumeDrugC2SPacket und DrugLevelDataSyncPacket.
            Das erste Paket, synchronisiert garnichts. Es bereitet nur die Synchronisation vor.
            Es kann auch in einer Klasse gemacht werden. Weil das Erste Paket, das SyncPaket verschickt.
            Also wird die erste Klasse quasi nicht gebraucht.
         */
        net.messageBuilder(ConsumeDrugC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ConsumeDrugC2SPacket::new)
                .encoder(ConsumeDrugC2SPacket::toBytes)
                .consumerMainThread(ConsumeDrugC2SPacket::handle)
                .add();
        net.messageBuilder(DrugLevelDataSyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(DrugLevelDataSyncC2SPacket::new)
                .encoder(DrugLevelDataSyncC2SPacket::toBytes)
                .consumerMainThread(DrugLevelDataSyncC2SPacket::handle)
                .add();

    }
    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),message);
    }
}
