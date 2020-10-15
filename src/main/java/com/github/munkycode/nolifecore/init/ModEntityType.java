package com.github.munkycode.nolifecore.init;

import com.github.munkycode.nolifecore.NoLifeCore;
import com.github.munkycode.nolifecore.entities.BotEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, NoLifeCore.MODID);

    public static final RegistryObject<EntityType<BotEntity>> BOT = ENTITY_TYPES.register("bot", () -> EntityType.Builder.create(BotEntity::new, EntityClassification.CREATURE)
            .size(0.60f, 1.80f)
            .build(new ResourceLocation(NoLifeCore.MODID, "bot").toString()));


}
