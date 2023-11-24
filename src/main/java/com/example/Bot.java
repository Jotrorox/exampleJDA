package com.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

// An example bot using JDA
public class Bot extends ListenerAdapter {
    public static void main(String[] args) {
        // Create the bot
        JDABuilder builder = JDABuilder.createDefault("BOT_TOKEN");

        builder.addEventListeners(new Bot());

        // Disable some cache features
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);

        // Disable the bulk delete feature
        builder.setBulkDeleteSplittingEnabled(false);

        // Set the bot's activity
        builder.setActivity(Activity.playing("with JDA!"));

        // Build the bot
        JDA bot = builder.build();

        // Register the bot's commands
        bot.updateCommands().addCommands(
            Commands.slash("ping", "Calculate the ping of the bot")
        ).queue();
    }

    // Handle slash command events
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Handle the ping command
        switch (event.getName()) {
            case "ping":
                // Calculate the ping
                long time = System.currentTimeMillis();

                // Acknowledge the command
                event.reply("Pong!").setEphemeral(true)
                    // Update the original response
                    .flatMap(v -> event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time))
                    .queue();
                break;
        
            default:
                break;
        }
    }
}