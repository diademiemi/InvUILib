package me.diademiemi.invuilib.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * The type Menu listener.
 */
public class MenuListener implements Listener {
    /**
     * On click.
     *
     * @param e the e
     */
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Menu menu = Menu.getMenu(player);
        if (menu != null) {
            if (e.getClickedInventory() == menu.getInventory()) e.setCancelled(true);
            if (menu.getButton(e.getRawSlot()) != null) {
                if (e.getCursor().getType() != Material.AIR) {
                    menu.getButton(e.getRawSlot()).onItemDrag(player, e.getCursor());
                } else if (e.isLeftClick()) {
                    menu.getButton(e.getRawSlot()).onLeftClick(player);
                } else if (e.isRightClick()) {
                    menu.getButton(e.getRawSlot()).onRightClick(player);
                }
            }
        }
    }

    /**
     * On inventory close.
     *
     * @param e the e
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Menu menu = Menu.getMenu((Player) e.getPlayer());
        if (menu != null) {
            if (menu.getInventory().equals(e.getInventory())) {
                menu.forceClose();
            }
        }
    }

    /**
     * On player quit.
     *
     * @param e the e
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Menu menu = Menu.getMenu(e.getPlayer());
        if (menu != null) {
            menu.forceClose();
        }
    }
}
