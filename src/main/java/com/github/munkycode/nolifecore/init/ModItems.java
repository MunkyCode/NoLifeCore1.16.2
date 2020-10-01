package com.github.munkycode.nolifecore.init;

import com.github.munkycode.nolifecore.NoLifeCore;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;



public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NoLifeCore.MODID);

    public static final RegistryObject<Item> EXAMPLE_INGOT = ITEMS.register("example_ingot",
            ()->new Item(new Item.Properties().group(NoLifeCore.TAB)));

    public static final RegistryObject<Item> EXAMPLE_ORE_ITEM = ITEMS.register("example_ore",
            ()-> new BlockItem(ModBlocks.EXAMPLE_ORE.get(), new Item.Properties().group(NoLifeCore.TAB)));


}

