package net.marvin.tutorialmod.block;

import net.marvin.tutorialmod.Tutorialmod;
import net.marvin.tutorialmod.item.ItemRegistry;
import net.marvin.tutorialmod.item.ModCreativeModeTab;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorialmod.MOD_ID);


    // Adding Blocks to Registry
    public static final RegistryObject<Block> ZIRCON_BLOCK =
            registerBlock("zircon_block",
                    () -> ModBlocks.ZIRCON_BLOCK_BLK,
                    ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> ZIRCON_ORE =
            registerBlock("zircon_ore",
                    () -> ModBlocks.ZIRCON_ORE_BLK,
                    ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE =
            registerBlock("deepslate_zircon_ore",
                    () -> ModBlocks.DEEPSLATE_ZIRCON_ORE_BLK,
                    ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> BLUEBERRY_CROP =
            BLOCKS.register("blueberry_crop",
                    ()-> ModBlocks.BLUEBERRY_CROP_BLK);
    public static final RegistryObject<Block> FISHHOOK =
            registerBlock("fishhook",
                    () -> ModBlocks.FISHHOOK_BLK,
                    ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> CUSTOM_SOIL =
            registerBlock("custom_soil",
                    () -> ModBlocks.CUSTOM_SOIL_BLK,
                    ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> RASPBERRY_BUSH =
            BLOCKS.register("raspberry_bush",
                    () -> ModBlocks.RASPBERRY_BUSH_BLK);



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }
    private static <T extends  Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ItemRegistry.ITEMS.register(name,() -> new BlockItem(block.get(), new Item.Properties().tab((tab)))
        );
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }


}
