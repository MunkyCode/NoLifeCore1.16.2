package com.github.munkycode.nolifecore;


import com.github.munkycode.nolifecore.entity.BotEntity;
import com.github.munkycode.nolifecore.init.ModBlocks;
import com.github.munkycode.nolifecore.init.ModEntityType;
import com.github.munkycode.nolifecore.init.ModItems;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NoLifeCore.MODID)
public class NoLifeCore {
    public static final String MODID = "nolifecore";
    public static final Logger LOGGER = LogManager.getLogger();

    public NoLifeCore(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);


        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.debug("Get A Life");
    }

    private void setup(final FMLCommonSetupEvent event){
        DeferredWorkQueue.runLater(()-> {
            GlobalEntityTypeAttributes.put(ModEntityType.BOT.get(), BotEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }

    @SubscribeEvent
    public void init(RegisterCommandsEvent event){
        botCommand.register(event.getDispatcher());
    }


    public static final ItemGroup TAB = new ItemGroup("nolifecore") {
        @Override
        public ItemStack createIcon(){
            return new ItemStack(ModItems.EXAMPLE_INGOT.get());
        }
    };

}


