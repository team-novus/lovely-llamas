package com.brunocollingridge.lovelyllamas.core;

import com.brunocollingridge.lovelyllamas.LovelyLlamas;
import com.brunocollingridge.lovelyllamas.common.entity.ai.FollowPlayerGoal;
import com.brunocollingridge.lovelyllamas.core.init.ItemInit;
import com.brunocollingridge.lovelyllamas.core.util.TradeUtils;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LovelyLlamas.Mod_ID)
public class LlamaEvents {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof LlamaEntity) {
            LlamaEntity llama = (LlamaEntity) event.getEntity();
            llama.goalSelector.addGoal(1, new FollowPlayerGoal(llama));
        }
    }

    /*
    Give the chest with contents back?

    This way feels cleaner because of the way you add chests/items to llamas in the first place.
    You don't right click a chest with items onto a llama, you put each item and the chest in specifically
     */
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget().getType() == EntityType.LLAMA) {
            PlayerEntity player = event.getPlayer();
            LlamaEntity llama = (LlamaEntity) event.getTarget();

            if (llama.hasChest() && player.isCrouching() && player.getHeldItem(Hand.MAIN_HAND).isEmpty()) {
                for (int i = 0; i < llama.horseChest.getSizeInventory(); ++i) {
                    if (i != 1) {
                        ItemStack stack = llama.horseChest.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            player.addItemStackToInventory(stack);
                            llama.horseChest.removeStackFromSlot(i);
                        }
                    }
                }

                player.addItemStackToInventory(new ItemStack(Items.CHEST));
                llama.setChested(false);
            }
        }
    }

    /*
    Has to be worked on; currently all chests generated with this loot table give at least one llama fleece
     */
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        LootTable table = event.getTable();
        String name = event.getName().toString();

        LootPool.Builder builder = new LootPool.Builder().name("fleece_inject");
        IRandomRange range;

        if (name.equals(LootTables.GAMEPLAY_FISHING_JUNK.toString()) ||
                name.equals(LootTables.BASTION_BRIDGE.toString()) ||
                name.equals(LootTables.BASTION_HOGLIN_STABLE.toString()) ||
                name.equals(LootTables.CHESTS_VILLAGE_VILLAGE_TANNERY.toString()) ||
                name.equals(LootTables.PIGLIN_BARTERING.toString())
        ) {
            if (!name.equals(LootTables.GAMEPLAY_FISHING_JUNK.toString())) {
                if (name.equals(LootTables.PIGLIN_BARTERING.toString())) range = RandomValueRange.of(4, 10);
                else range = RandomValueRange.of(1, 3);

                builder.acceptFunction(SetCount.builder(range));
            }
            LootPool pool = builder.addEntry(TableLootEntry.builder(new ResourceLocation(LovelyLlamas.Mod_ID, "injects/llama_fleece")))
                    .build();
            table.addPool(pool);
        }
    }

    /*
    Check to make sure price and amounts are correct
    Currently it is 1 emerald for 8 llama fleece
     */
    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        event.getGenericTrades().addAll(ImmutableSet.of(
                new TradeUtils.ItemsForEmeraldsTrade(new ItemStack(ItemInit.LLAMA_FLEECE.get()), 1, 8, 8, 2)
        ));
    }
}