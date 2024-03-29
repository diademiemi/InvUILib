package me.diademiemi.invuilib.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;

public class Menu extends ActiveMenu {

    /**
     * Create menu
     * @param size the size of the GUI
     * @param title the title of the GUI
     * @param player the Player for the GUI
     */
    public Menu(Player player, MenuSize size, String title, HashMap<Integer, MenuButton> buttons) {
        this.title = title;
        this.player = player;
        this.buttons = buttons;

        if (size == MenuSize.HALF_ROW) {
            this.inventory = Bukkit.getServer().createInventory(null, InventoryType.HOPPER, title);
        } else {
            this.inventory = Bukkit.getServer().createInventory(null, size.getSize(), title);
        }

        this.bakeButtons();
        guiMap.put(player, this);
    }

}
