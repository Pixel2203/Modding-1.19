package net.marvin.tutorialmod.materials;


import net.marvin.tutorialmod.item.ItemRegistry;
import net.marvin.tutorialmod.materials.ZirconMat;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class ModMaterials {

    public static final ArmorMaterial ZirconMaterial = new ZirconMat(
            "zirconmat",
            500,
            0f,
            new int[] {20, 40, 50, 10},
            300,
            0f,
            SoundEvents.ARMOR_EQUIP_GOLD,
            () -> Ingredient.of(ItemRegistry.ZIRCON.get())
    );


}
