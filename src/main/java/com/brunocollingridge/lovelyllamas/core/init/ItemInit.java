package com.brunocollingridge.lovelyllamas.core.init;

import com.brunocollingridge.lovelyllamas.LovelyLlamas;
import com.brunocollingridge.lovelyllamas.common.item.FleeceArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            LovelyLlamas.Mod_ID);

    public static final RegistryObject<Item> LLAMA_FLEECE = ITEMS.register("llama_fleece",
            () -> new Item(new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> FLEECE_CHULLO = ITEMS.register("fleece_chullo",
            () -> new FleeceArmorItem(EquipmentSlotType.HEAD));

    public static final RegistryObject<Item> FLEECE_PONCHO = ITEMS.register("fleece_poncho",
            () -> new FleeceArmorItem(EquipmentSlotType.CHEST));

    public static final RegistryObject<Item> FLEECE_SLACKS = ITEMS.register( "fleece_slacks",
            () -> new FleeceArmorItem(EquipmentSlotType.LEGS));

    public static final RegistryObject<Item> FLEECE_SLIPPERS = ITEMS.register("fleece_slippers",
            () -> new FleeceArmorItem(EquipmentSlotType.FEET));
}
