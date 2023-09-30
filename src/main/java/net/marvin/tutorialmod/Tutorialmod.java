package net.marvin.tutorialmod;

import com.mojang.logging.LogUtils;
import net.marvin.tutorialmod.block.BlockRegistry;
import net.marvin.tutorialmod.block.entity.ModBlockEntities;
import net.marvin.tutorialmod.item.ItemRegistry;
import net.marvin.tutorialmod.networking.ModMessages;
import net.marvin.tutorialmod.painting.PaintingsRegistry;
import net.marvin.tutorialmod.screen.GemInfusingStationScreen;
import net.marvin.tutorialmod.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutorialmod.MOD_ID)
public class Tutorialmod
{
    // Very Important Commment!
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tutorialmod";
    public static final String LOGGER_PREFIX = "[Tutorialmod]";
    public static final Logger LOGGER = LogUtils.getLogger();
    public Tutorialmod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(modEventBus); // Adds Items
        BlockRegistry.register(modEventBus); // Adds Blocks
        ConfigFreatureInit.CONFIGURED_FEATURES.register(modEventBus); // Ore Generation
        PlacedFeatureInit.PLACED_FEATURES.register(modEventBus); // Painting
        PaintingsRegistry.register(modEventBus);  //Painting
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        modEventBus.addListener(this::commonSetup);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        ModMessages.register();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.GEM_INFUSING_STATION_MENU.get(), GemInfusingStationScreen::new);
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUEBERRY_CROP.get(), RenderType.cutout());
        }
    }
}
