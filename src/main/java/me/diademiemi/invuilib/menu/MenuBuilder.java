package me.diademiemi.invuilib.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuBuilder {
    public String title;
    public MenuSize size;

    public Player player;

    public HashMap<Integer, MenuButton> buttons;

    public MenuBuilder(String title, MenuSize size, Player player) {
        this.title = title;
        this.size = size;
        this.player = player;
        this.buttons = new HashMap<Integer, MenuButton>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(MenuSize size) {
        this.size = size;
    }

    public void addButton(MenuButton button, int... slot) {
        if (slot.length == 0) {
            buttons.put(buttons.size() + 1, button);
        } else {
            for (int i : slot) {
                buttons.put(i, button);
            }
        }
    }

    public Menu build() {
        return new Menu(player, size, title, buttons);
    }

}
