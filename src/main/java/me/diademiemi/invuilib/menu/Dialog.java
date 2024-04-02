package me.diademiemi.invuilib.menu;

import me.diademiemi.invuilib.InvUILib;
import org.bukkit.entity.Player;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.connection.GeyserConnection;

/**
 * A dialog is a wrapper around a menu that is intended to be shown to a player.
 * It is a way to encapsulate the creation and showing of a menu.
 * It is intended as a safe way to show a menu to a player, ensuring that the player's current menu is closed before showing the new menu.
 * The recommended way to use this is to implement this class and override the create method to return the menu you want to show.
 * MenuBuilder is provided to help with creating menus.
 */
public interface Dialog {
    /**
     * Show.
     *
     * @param player the player
     * @param args   the args
     */
    default void show(Player player, Object... args) {
        try {
            close(player);
        } catch (NullPointerException e) {
            // Do nothing
        }

        Menu menu = create(player, args);
        if (menu != null) {
            GeyserConnection connection = GeyserApi.api().connectionByUuid(player.getUniqueId());

            if (connection == null) {
                // We can assume this is a Java player
                menu.open();
            } else {
                // We can assume this is a Bedrock player
                // For bedrock players, add a 2 tick delay, or the UI will not update its title
                InvUILib.getPlugin().getServer().getScheduler().runTaskLater(InvUILib.getPlugin(), menu::open, 2);
            }

//            menu.open();
        }
    }

    /**
     * Close.
     *
     * @param player the player
     */
    default void close(Player player) {
        Menu.getMenu(player).close();
    }

    /**
     * Create menu.
     *
     * @param p    the p
     * @param args the args
     * @return the menu
     */
    Menu create(Player p, Object... args);
}
