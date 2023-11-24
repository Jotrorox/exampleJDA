package com.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot {
    public static void main( String[] args ) {
        JDABuilder builder = JDABuilder.createDefault("BOT_TOKEN");

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing("with JDA!"));
        builder.build();
    }
}