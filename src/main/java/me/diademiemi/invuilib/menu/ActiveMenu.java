package me.diademiemi.invuilib.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class ActiveMenu {
    public static HashMap<Player, ActiveMenu> guiMap = new HashMap<Player, ActiveMenu>();
    public HashMap<Integer, MenuButton> buttons;
    public Inventory inventory;
    public Player player;
    public String title;

    /**
     * @param activeMenu GUI to add
     */
    public void addActiveMenu(ActiveMenu activeMenu) {
        guiMap.put(player, activeMenu);
    }

    /**
     * @param activeMenu GUI to remove
     */
    public void removeActiveMenu(ActiveMenu activeMenu) {
        guiMap.remove(player, activeMenu);
    }

    /**
     * @return all loaded GUIs
     */
    public static HashMap<Player, ActiveMenu> getGUIMap() {
        return guiMap;
    }

    /**
     * @param player
     * @return the GUI of the given inventory and player
     */
    public static ActiveMenu getActiveMenu(Player player) {
        return guiMap.get(player);
    }

    public void open() {
        player.openInventory(inventory);
    }

    public void close() {
        removeActiveMenu(this);
        player.closeInventory();
    }

    public MenuButton getButton(int slot) {
        return buttons.get(slot);
    }

    public void bakeButtons() {
        for (Map.Entry<Integer, MenuButton> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }
    }

    public void updateButton(int slot, MenuButton button) throws IndexOutOfBoundsException {
        if (slot >= inventory.getSize()) {
            throw new IndexOutOfBoundsException("Slot is out of bounds");
        } else {
            buttons.put(slot, button);
            inventory.setItem(slot, button.getItemStack());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}
