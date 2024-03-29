package me.diademiemi.invuilib;

import me.diademiemi.invuilib.command.CommandHandler;
import me.diademiemi.invuilib.input.ChatListener;
import me.diademiemi.invuilib.menu.MenuListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvUILib extends JavaPlugin {

    private static InvUILib plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MenuListener(), this);
        pm.registerEvents(new ChatListener(), this);

        // Load commands
        getCommand("invuilib").setExecutor(new CommandHandler());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static InvUILib getPlugin() {
        return plugin;
    }

}
