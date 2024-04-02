package me.diademiemi.invuilib.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Menu button.
 */
public class MenuButton {
    private ItemStack stack;

    /**
     * Instantiates a new Menu button.
     */
    public MenuButton() {
        this.stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = this.stack.getItemMeta();
        meta.setDisplayName(" ");
        this.stack.setItemMeta(meta);
    }

    /**
     * Instantiates a new Menu button.
     *
     * @param material the material
     * @param texts    the texts
     */
    public MenuButton(Material material, String... texts) {
        this(material, 1, texts);
    }

    /**
     * Instantiates a new Menu button.
     *
     * @param material the material
     * @param amount   the amount
     * @param texts    the texts
     */
    public MenuButton(Material material, int amount, String... texts) {
        String name;
        if (texts.length == 0) {
            name = " ";
        } else {
            name = texts[0];
        }
        ArrayList<String> lore = new ArrayList<String>();
        for (int i = 1; i < texts.length; i++) {
            lore.add(texts[i]);
        }
        stack = new ItemStack(material, amount);

        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        stack.setItemMeta(itemMeta);
    }

    /**
     * Instantiates a new Menu button.
     *
     * @param stack the stack
     */
    public MenuButton(ItemStack stack) {
        this.stack = stack;
    }

    /**
     * Update amount.
     *
     * @param amount the amount
     */
    public void updateAmount(int amount) {
        stack.setAmount(amount);
    }

    /**
     * Update name.
     *
     * @param string the string
     */
    public void updateName(String string) {
        stack.getItemMeta().setDisplayName(string);
    }

    /**
     * Update lore.
     *
     * @param lore the lore
     */
    public void updateLore(String... lore) {
        stack.getItemMeta().setLore(Arrays.asList(lore));
    }

    /**
     * Gets item stack.
     *
     * @return the item stack
     */
    public ItemStack getItemStack() {
        return stack;
    }

    /**
     * Sets item stack.
     *
     * @param stack the stack
     */
    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }

    /**
     * On left click.
     *
     * @param p the p
     */
    public void onLeftClick(Player p) { }

    /**
     * On right click.
     *
     * @param p the p
     */
    public void onRightClick(Player p) { }

    /**
     * On item drag.
     *
     * @param p  the p
     * @param is the is
     */
    public void onItemDrag(Player p, ItemStack is) { }

}
