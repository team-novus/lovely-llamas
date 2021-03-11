package com.brunocollingridge.lovelyllamas.common.item;

import com.brunocollingridge.lovelyllamas.LovelyLlamas;
import com.brunocollingridge.lovelyllamas.client.model.FleeceChulloModel;
import com.brunocollingridge.lovelyllamas.client.model.FleecePonchoModel;
import com.brunocollingridge.lovelyllamas.client.model.FleeceSlacksModel;
import com.brunocollingridge.lovelyllamas.client.model.FleeceSlippersModel;
import com.brunocollingridge.lovelyllamas.core.init.ItemInit;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FleeceArmorItem extends ArmorItem {
    public static final LlamaArmorMaterial FLEECE_MATERIAL = new LlamaArmorMaterial(new ResourceLocation(LovelyLlamas.Mod_ID, "fleece"), 5, new int[]{2, 3, 4, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.fromItems(ItemInit.LLAMA_FLEECE.get()));

    public FleeceArmorItem(EquipmentSlotType slotType) {
        super(FLEECE_MATERIAL, slotType, new Item.Properties().group(ItemGroup.COMBAT));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        switch (this.slot) {
            default:
            case HEAD:
                return FleeceChulloModel.get(1.0F);
            case CHEST:
                return FleecePonchoModel.get(1.0F);
            case LEGS:
                return FleeceSlacksModel.get(1.0F);
            case FEET:
                return FleeceSlippersModel.get(1.0F);
        }
    }
}