package com.github.munkycode.nolifecore;


import com.github.munkycode.nolifecore.init.ModItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = NoLifeCore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(setup(new Item(new Item.Properties().group(ModItemGroups.NOLIFECORE)), "example_ingot"));

    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f)), "example_ore"));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(NoLifeCore.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }

}
