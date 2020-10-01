package com.github.munkycode.nolifecore;


import com.github.munkycode.nolifecore.init.ModBlocks;
import com.github.munkycode.nolifecore.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NoLifeCore.MODID)
public class NoLifeCore {
    public static final String MODID = "nolifecore";
    public static final Logger LOGGER = LogManager.getLogger();

    public NoLifeCore(){
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.debug("Get A Life");
    }


    public static final ItemGroup TAB = new ItemGroup("nolifecore") {
        @Override
        public ItemStack createIcon(){
            return new ItemStack(ModItems.EXAMPLE_INGOT.get());
        }
    };

}
