package me.diademiemi.invuilib.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuButton {
    private ItemStack stack;

    public MenuButton() {
        this.stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = this.stack.getItemMeta();
        meta.setDisplayName(" ");
        this.stack.setItemMeta(meta);
    }

    public MenuButton(Material material, String... texts) {
        this(material, 1, texts);
    }

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

    public MenuButton(ItemStack stack) {
        this.stack = stack;
    }

    public void updateAmount(int amount) {
        stack.setAmount(amount);
    }

    public void updateName(String string) {
        stack.getItemMeta().setDisplayName(string);
    }

    public void updateLore(String... lore) {
        stack.getItemMeta().setLore(Arrays.asList(lore));
    }

    public ItemStack getItemStack() {
        return stack;
    }

    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }

    public void onLeftClick(Player p) { }

    public void onRightClick(Player p) { }

    public void onItemDrag(Player p, ItemStack is) { }

}
