package me.diademiemi.invuilib.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * A menu is a wrapper for an inventory that allows for easy creation and manipulation of GUIs
 * Menus contain "buttons" which are the clickable items in the GUI. These can be added with the addButton method.
 * Menus can be opened and closed with the open and close methods.
 * This also contains a static map of every menu that is currently open, which can be accessed with getMenuMap.
 * Menus are linked to a player, this is to prevent exploits where a player could force open a menu without permission
 * Menus can be set to prevent closing, this is useful for menus that require a player to complete an action before closing
 */
public class Menu {
    public static HashMap<Player, Menu> menuMap = new HashMap<Player, Menu>();
    public HashMap<Integer, MenuButton> buttons;
    public Inventory inventory;
    public Player player;
    public String title;
    private Runnable onOpen;
    private Runnable onClose;
    private Runnable onForceClose;

    /**
     * Create menu
     *
     * @param size         the size of the GUI
     * @param title        the title of the GUI
     * @param player       the Player for the GUI
     * @param buttons      the buttons of the GUI
     * @param onOpen       the action to run when the GUI is opened (by plugin)
     * @param onClose      the action to run when the GUI is closed (by plugin)
     * @param onForceClose the action to run when the GUI is force closed (by player)
     */
    public Menu(Player player, MenuSize size, String title, HashMap<Integer, MenuButton> buttons, Runnable onOpen, Runnable onClose, Runnable onForceClose) {
        this.title = title;
        this.player = player;
        this.buttons = buttons;
        this.onOpen = onOpen;
        this.onClose = onClose;
        this.onForceClose = onForceClose;

        if (size == MenuSize.HALF_ROW) {
            this.inventory = Bukkit.getServer().createInventory(null, InventoryType.HOPPER, title);
        } else {
            this.inventory = Bukkit.getServer().createInventory(null, size.getSize(), title);
        }

        this.bakeButtons();
        menuMap.put(player, this);
    }

    /**
     * @return all loaded GUIs
     */
    public static HashMap<Player, Menu> getMenuMap() {
        return menuMap;
    }

    /**
     * @param player the player to get the GUI of
     * @return the GUI of the given inventory and player
     */
    public static Menu getMenu(Player player) {
        return menuMap.get(player);
    }

    /**
     * @param activeMenu GUI to add
     */
    public void addMenu(Menu activeMenu) {
        menuMap.put(player, activeMenu);
    }

    /**
     * @param activeMenu GUI to remove
     */
    public void removeMenu(Menu activeMenu) {
        menuMap.remove(player, activeMenu);
    }

    /**
     * Open the GUI for the player
     * Happens when the plugin calls the open method
     */
    public void open() {
        player.openInventory(inventory);
        onOpen();
    }

    /**
     * Close the GUI and remove it from the GUI map
     * Happens when the plugin calls the close method
     */
    public void close() {
        removeMenu(this);
        player.closeInventory();
        onClose();
    }

    /**
     * Close the GUI and remove it from the GUI map
     * Happens when the player closes the GUI with escape or by logging out
     */
    public void forceClose() {
        removeMenu(this);
        player.closeInventory();
        onForceClose();
    }


    /**
     * @param slot the slot to get the button of
     * @return the button of the given slot
     */
    public MenuButton getButton(int slot) {
        return buttons.get(slot);
    }

    /**
     * @param slot   the slot to update the button of
     * @param button the button to update
     * @throws IndexOutOfBoundsException if the slot is out of bounds
     */
    public void updateButton(int slot, MenuButton button) throws IndexOutOfBoundsException {
        if (slot >= inventory.getSize()) {
            throw new IndexOutOfBoundsException("Slot is out of bounds");
        } else {
            buttons.put(slot, button);
        }
    }

    /**
     * @param button the button to add
     * @param slot   the slots to add the button to
     * @throws IndexOutOfBoundsException if the slot is out of bounds
     */
    public void addButton(MenuButton button, int... slot) throws IndexOutOfBoundsException {
        if (slot.length == 0) {
            buttons.put(buttons.size() + 1, button); // If no slot is specified, add to the next available slot. NOT RECOMMENDED
        } else {
            for (int i : slot) {
                buttons.put(i, button);
            }
        }
    }

    /**
     * @param slot the slot to remove the button from
     */
    public void removeButton(int slot) {
        buttons.remove(slot);
    }

    /**
     * Bake the buttons into the inventory
     */
    public void bakeButtons() {
        for (Map.Entry<Integer, MenuButton> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }
    }

    /**
     * @return the inventory of the GUI
     */
    public Inventory getInventory() {
        return inventory;
    }

    public void onOpen() {
        onOpen.run();
    }

    public void onClose() {
        onClose.run();
    }

    public void onForceClose() {
        onForceClose.run();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Runnable getOnOpen() {
        return onOpen;
    }

    public void setOnOpen(Runnable onOpen) {
        this.onOpen = onOpen;
    }

    public Runnable getOnClose() {
        return onClose;
    }

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }

    public Runnable getOnForceClose() {
        return onForceClose;
    }

    public void setOnForceClose(Runnable onForceClose) {
        this.onForceClose = onForceClose;
    }
}
