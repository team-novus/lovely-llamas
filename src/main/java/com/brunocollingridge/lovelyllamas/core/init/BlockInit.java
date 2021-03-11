package com.brunocollingridge.lovelyllamas.core.init;

import com.brunocollingridge.lovelyllamas.LovelyLlamas;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            LovelyLlamas.Mod_ID);
}
