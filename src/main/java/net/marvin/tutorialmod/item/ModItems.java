package net.marvin.tutorialmod.item;

import net.marvin.tutorialmod.item.custom.MagicPaste;
import net.marvin.tutorialmod.properties.CustomPillProperties;
import net.marvin.tutorialmod.item.custom.drugs.PillItem;
import net.marvin.tutorialmod.block.BlockRegistry;
import net.marvin.tutorialmod.item.custom.EightBallItem;
import net.marvin.tutorialmod.item.custom.FertilizerItem;

import net.marvin.tutorialmod.materials.ModMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.common.Mod;

public class ModItems {

    // Tools
    public static final SwordItem ZIRCON_SWORD_ITM =
            new SwordItem(
                    Tiers.DIAMOND,
                    50,
                    75,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final AxeItem ZIRCON_AXE_ITM =
            new AxeItem(
                    Tiers.DIAMOND,
                    50,
                    75,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final PickaxeItem ZIRCON_PICKAXE_ITM =
            new PickaxeItem(
                    Tiers.DIAMOND,
                    50,
                    75,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final HoeItem ZIRCON_HOE_ITM =
            new HoeItem(
                    Tiers.DIAMOND,
                    50,
                    75,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final ShovelItem ZIRCON_SHOVEL_ITM =
            new ShovelItem(
                    Tiers.DIAMOND,
                    50,
                    75,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );

    // Armor
    public static final ArmorItem ZIRCON_HELMET_ITM =
            new ArmorItem(
                    ModMaterials.ZirconMaterial,
                    EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final ArmorItem ZIRCON_CHESTPLATE_ITM =
            new ArmorItem(
                    ModMaterials.ZirconMaterial,
                    EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final ArmorItem ZIRCON_LEGGINGS_ITM =
            new ArmorItem(
                    ModMaterials.ZirconMaterial,
                    EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final ArmorItem ZIRCON_BOOTS_ITM =
            new ArmorItem(
                    ModMaterials.ZirconMaterial,
                    EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );

    // Mysc
    public static final EightBallItem EIGHT_BALL_ITM =
            new EightBallItem(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final Item BLUEBERRY_SEEDS_ITM =
            new ItemNameBlockItem(
                    BlockRegistry.BLUEBERRY_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final Item BLUEBERRY_ITM =
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).food(new FoodProperties.Builder().nutrition(3).saturationMod(3).build())
            );
    // Ores and Ingots
    public static final Item ZIRCON_ITM =
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final Item RAW_ZIRCON_ITM =
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );

    // Custom Items
    public static final Item CUSTOM_MODEL_TEST_ITM =
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );
    public static final Item FERTILIZER_ITM =
            new FertilizerItem(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );


    public static final Item RASPBERRY_ITM =
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(3).build())
            );

    public static final Item RASPBERRY_BUSH_ITM =
            new ItemNameBlockItem(
                    BlockRegistry.RASPBERRY_BUSH.get(),
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );

    public static final Item CAFFEINE_PILL_ITM =
            new PillItem(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB),
                    CustomPillProperties.CAFFEINE_PILL_PROPERTIES,
                    1,
                    25
            );
    public static final Item PAINKILLER_PILL_ITM =
            new PillItem(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB),
                    CustomPillProperties.PAINKILLER_PILL_PROPERTIES,
                    1,
                    25

            );
    public static final Item MORPHINE_SYRINGE_ITM =
            new PillItem(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB),
                    CustomPillProperties.MORPHINE_SYRINGE_PROPERTIES,
                    1,
                    250,
                    5

            );
    public static final Item MAGIC_PASTE_ITM =
            new MagicPaste(
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
            );



}
