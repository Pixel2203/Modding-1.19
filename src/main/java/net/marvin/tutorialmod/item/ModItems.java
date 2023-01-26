package net.marvin.tutorialmod.item;

import net.marvin.tutorialmod.block.BlockRegistry;
import net.marvin.tutorialmod.block.ModBlocks;
import net.marvin.tutorialmod.item.custom.EightBallItem;
import net.marvin.tutorialmod.item.custom.tools.ZirconHoeItem;
import net.marvin.tutorialmod.materials.ModMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;

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
            new ZirconHoeItem(
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

}
