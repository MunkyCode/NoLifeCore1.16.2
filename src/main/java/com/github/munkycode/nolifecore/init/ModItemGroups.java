package com.github.munkycode.nolifecore.init;

import com.github.munkycode.nolifecore.NoLifeCore;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class ModItemGroups {
    public static class ModItemGroup extends ItemGroup{

        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(String name, final Supplier<ItemStack> iconSupplier){
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon(){
            return iconSupplier.get();
        }

    }
    public static final ItemGroup NOLIFECORE = new ModItemGroup(NoLifeCore.MODID, () -> new ItemStack(ModItems.EXAMPLE_INGOT));
}
