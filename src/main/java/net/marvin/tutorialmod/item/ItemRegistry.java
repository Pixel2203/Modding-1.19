package net.marvin.tutorialmod.item;

import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    ForgeRegistries.ITEMS,
                    Tutorialmod.MOD_ID
            );

    public static final RegistryObject<Item> ZIRCON =
            ITEMS.register("zircon",() -> ModItems.ZIRCON_ITM);

    public static final RegistryObject<Item> RAW_ZIRCON =
            ITEMS.register("raw_zircon",() -> ModItems.RAW_ZIRCON_ITM);

    public static final RegistryObject<Item> EIGHT_BALL =
            ITEMS.register("eight_ball",() -> ModItems.EIGHT_BALL_ITM);

    public static final RegistryObject<Item> BLUEBERRY_SEEDS =
        ITEMS.register("blueberry_seeds", () -> ModItems.BLUEBERRY_SEEDS_ITM);



    public static final RegistryObject<Item> BLUEBERRY =
            ITEMS.register("blueberry", () -> ModItems.BLUEBERRY_ITM);

    public static final RegistryObject<ArmorItem> ZIRCON_HELMET =
            ITEMS.register("zircon_helmet",() -> ModItems.ZIRCON_HELMET_ITM);
    public static final RegistryObject<ArmorItem> ZIRCON_CHESTPLATE =
            ITEMS.register("zircon_chestplate",() -> ModItems.ZIRCON_CHESTPLATE_ITM);
    public static final RegistryObject<ArmorItem> ZIRCON_LEGGINGS =
            ITEMS.register("zircon_leggings",() -> ModItems.ZIRCON_LEGGINGS_ITM);
    public static final RegistryObject<ArmorItem> ZIRCON_BOOTS =
            ITEMS.register("zircon_boots",() -> ModItems.ZIRCON_BOOTS_ITM);

    public static final RegistryObject<PickaxeItem> ZIRCON_PICKAXE =
            ITEMS.register("zircon_pickaxe", () -> ModItems.ZIRCON_PICKAXE_ITM);
    public static final RegistryObject<AxeItem> ZIRCON_AXE =
            ITEMS.register("zircon_axe", () -> ModItems.ZIRCON_AXE_ITM);
    public static final RegistryObject<SwordItem> ZIRCON_SWORD =
            ITEMS.register("zircon_sword", () -> ModItems.ZIRCON_SWORD_ITM);
    public static final RegistryObject<HoeItem> ZIRCON_HOE =
            ITEMS.register("zircon_hoe", () -> ModItems.ZIRCON_HOE_ITM);
    public static final RegistryObject<ShovelItem> ZIRCON_SHOVEL =
            ITEMS.register("zircon_shovel", () -> ModItems.ZIRCON_SHOVEL_ITM);


    // Custom Itmes
    public static final RegistryObject<Item> CUSTOM_MODEL_ITEM =
            ITEMS.register("custom_item", () -> ModItems.CUSTOM_MODEL_TEST_ITM);
    public static final RegistryObject<Item> FERTILIZER =
            ITEMS.register("fertilizer", () -> ModItems.FERTILIZER_ITM);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
