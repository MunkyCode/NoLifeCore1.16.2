package com.github.munkycode.nolifecore.client.entity.render;

import com.github.munkycode.nolifecore.NoLifeCore;
import com.github.munkycode.nolifecore.client.entity.model.BotModel;
import com.github.munkycode.nolifecore.entity.BotEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class BotRenderer extends LivingRenderer<BotEntity, BotModel<BotEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(NoLifeCore.MODID, "textures/entity/bot.png");

    public BotRenderer(EntityRendererManager rendererManagerIn){
        super(rendererManagerIn, new BotModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(BotEntity entity) {
        return TEXTURE;
    }

}
