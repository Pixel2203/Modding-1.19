package net.marvin.tutorialmod.materials;

import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class ZirconMat implements ArmorMaterial {

    private final String name;
    private final float knockbackResistance;
    private final int[] protection;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final int[] DURABILITY_MULTIPLIER = new int[] {
            13,
            15,
            16,
            11
    };
    private final int durability;
    public ZirconMat(String name, int durability, float knockbackResistance, int[] protection, int enchantability,float toughness, SoundEvent equipSound, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.knockbackResistance = knockbackResistance;
        this.protection = protection;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.repairMaterial = repairMaterial;
        this.toughness = toughness;
        this.durability = durability;
    }
    @Override
    public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
        return this.DURABILITY_MULTIPLIER[equipmentSlot.getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
        return this.protection[equipmentSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return Tutorialmod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
