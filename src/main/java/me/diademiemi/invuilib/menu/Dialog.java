package me.diademiemi.invuilib.menu;

import org.bukkit.entity.Player;

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
        ActiveMenu.getActiveMenu(player).close();
    }

    Menu create(Player p, Object... args);
}
