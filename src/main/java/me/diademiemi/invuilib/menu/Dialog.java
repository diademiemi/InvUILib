package me.diademiemi.invuilib.menu;

import org.bukkit.entity.Player;

/**
 * A dialog is a wrapper around a menu that is intended to be shown to a player.
 * It is a way to encapsulate the creation and showing of a menu.
 * It is intended as a safe way to show a menu to a player, ensuring that the player's current menu is closed before showing the new menu.
 * The recommended way to use this is to implement this class and override the create method to return the menu you want to show.
 * MenuBuilder is provided to help with creating menus.
 */
public interface Dialog {
    default void show(Player player, Object... args) {
        try {
            close(player);
        } catch (NullPointerException e) {
            // Do nothing
        }

        Menu menu = create(player, args);
        if (menu != null) {
            menu.open(); // Sometimes it is intended to return null
        }
    }

    default void close(Player player) {
        Menu.getMenu(player).close();
    }

    Menu create(Player p, Object... args);
}
