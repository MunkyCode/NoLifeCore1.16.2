package com.github.munkycode.nolifecore;

import com.github.munkycode.nolifecore.entity.BotEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.Collection;
import java.util.Collections;

public class botCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("botclear").executes((source) -> clearInventory(source.getSource(), ImmutableList.of(source.getSource().assertIsEntity()))));
    }

    private static int clearInventory(CommandSource source, Collection<? extends Entity> entities){
        int i = 0;
        for(Entity e: entities){
            if(e instanceof BotEntity){
                ((BotEntity) e).inventory.clear();
            }
        }
        return i;

    }
}
