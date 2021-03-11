package com.brunocollingridge.lovelyllamas.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

import java.util.Random;

public class TradeUtils {
    public static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
        private final ItemStack itemstack;
        private final int stackSize;
        private final int receivedSize;
        private final int maxUses;
        private final int givenExp;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(ItemStack stack, int emeraldSize, int recievedSize, int maxUses, int givenExp) {
            this(stack, emeraldSize, recievedSize, maxUses, givenExp, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack stack, int emeraldSize, int recievedSize, int maxUses, int givenExp, float priceMultiplier) {
            this.itemstack = stack;
            this.stackSize = emeraldSize;
            this.receivedSize = recievedSize;
            this.maxUses = maxUses;
            this.givenExp = givenExp;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity entity, Random random) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.stackSize), new ItemStack(this.itemstack.getItem(), this.receivedSize), this.maxUses, this.givenExp, this.priceMultiplier);
        }
    }
}