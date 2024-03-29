package me.diademiemi.invuilib.test;

import me.diademiemi.invuilib.menu.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DgTest implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {

        MenuBuilder builder = new MenuBuilder("Test menu", MenuSize.SIX_ROWS, p);

        builder.addButtonByColumnRow(new MenuButton(Material.LIME_CONCRETE, 1, "Top Left") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the top left button!");
            }

        }, 0, 0, true);

        // Top right
        builder.addButtonByColumnRow(new MenuButton(Material.RED_CONCRETE, 1, "Top Right") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the top right button!");
            }
        }, 6, 0, true);

        // Bottom left
        builder.addButtonByColumnRow(new MenuButton(Material.BLUE_CONCRETE, 1, "Bottom Left") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the bottom left button!");
            }
        }, 0, 5, true);

        // Bottom right
        builder.addButtonByColumnRow(new MenuButton(Material.YELLOW_CONCRETE, 1, "Bottom Right") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the bottom right button!");
            }
        }, 6, 5, true);

        // Center
        builder.addButtonByColumnRow(new MenuButton(Material.PURPLE_CONCRETE, 1, "Center") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the center button!");
            }
        }, 3, 2, true);


        return builder.build();
    }
}
