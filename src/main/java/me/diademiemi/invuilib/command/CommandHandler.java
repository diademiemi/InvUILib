package me.diademiemi.invuilib.command;

import me.diademiemi.invuilib.InvUILib;
import me.diademiemi.invuilib.test.DgTest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.geysermc.api.util.UiProfile;
import org.geysermc.geyser.api.connection.GeyserConnection;
import org.geysermc.geyser.api.GeyserApi;

import java.util.List;

/**
 * The type Command handler.
 */
public class CommandHandler implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /invuilib test/info <player>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "info":
                if (!sender.hasPermission("invuilib.info")) {
                    sender.sendMessage("You do not have permission to use this command.");
                    return true;
                }
                return infoCommand(sender, args);
            case "test":
                if (!sender.hasPermission("invuilib.test")) {
                    sender.sendMessage("You do not have permission to use this command.");
                    return true;
                }
                return testCommand(sender, args);
            default:
                sender.sendMessage("Unknown subcommand. Usage: /invuilib test/info <player>");
                return true;

        }
    }

    /**
     * Info command boolean.
     *
     * @param sender the sender
     * @param args   the args
     * @return the boolean
     */
    public boolean infoCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /invuilib info <player>");
            return true;
        }
        // Get player by name or UUID (args[1]) here
        Player player = null;

        try {
            player = InvUILib.getPlugin().getServer().getPlayer(args[1]);
        } catch (Exception e) {
            player = InvUILib.getPlugin().getServer().getPlayer(java.util.UUID.fromString(args[1]));
        }

        GeyserConnection connection = GeyserApi.api().connectionByUuid(player.getUniqueId());

        if (connection == null) {
            sender.sendMessage("Player is playing on Java edition with classic GUI.");
            return true;
        } else if (connection.uiProfile() == UiProfile.CLASSIC) {
            sender.sendMessage("Player is playing on Bedrock edition with classic GUI.");
            return true;
        } else if (connection.uiProfile() == UiProfile.POCKET) {
            sender.sendMessage("Player is playing on Bedrock edition with pocket GUI.");
            return true;
        } else {
            sender.sendMessage("Player is playing on an unknown edition.");
            return true;
        }
    }

    /**
     * Test command boolean.
     *
     * @param sender the sender
     * @param args   the args
     * @return the boolean
     */
    public boolean testCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /invuilib test <player>");
            return true;
        }

        // Get player by name or UUID (args[1]) here
        Player player = null;
        try {
            player = InvUILib.getPlugin().getServer().getPlayer(args[1]);
        } catch (Exception e) {
            player = InvUILib.getPlugin().getServer().getPlayer(java.util.UUID.fromString(args[1]));
        }

        // Open a test menu for the player here
        if (player != null) {
            // Open a test menu for the player here
            new DgTest().show((Player) player);
        } else {
            sender.sendMessage("Player not found.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
