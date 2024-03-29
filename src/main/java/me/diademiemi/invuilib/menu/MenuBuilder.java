package me.diademiemi.invuilib.menu;

import me.diademiemi.invuilib.util.PlayerUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuBuilder {
    public String title;
    public MenuSize size;

    public Player player;

    public HashMap<Integer, MenuButton> buttons;
    private boolean preventClose;

    public MenuBuilder(String title, MenuSize size, Player player) {
        this.title = title;
        this.size = size;
        this.player = player;
        this.buttons = new HashMap<Integer, MenuButton>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPreventClose(boolean preventClose) {
        this.preventClose = preventClose;
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

    private int slotByColumnRow(int column, int row) {
        // Starting on the top left
        return column + row * 9;
    }

    private int slotByColumnRow(int column, int row, PlayerUtil.MenuType menuType) {
        // Starting on the top left
        // If the player is on Bedrock edition with POCKET menu, we use 7 columns instead of 9
        // For Java and Bedrock CLASSIC, we use 9 columns
        // For these, we add +1 for the first row and +2 for every other row, to skip the outer columns
        // E.g.
        // column 0 row 0 on Bedrock is 0, on Java is 1
        // column 1 row 0 on Bedrock is 1, on Java is 2
        // column 0 row 1 on Bedrock is 7, on Java is 9
        // column 1 row 1 on Bedrock is 8, on Java is 10
        // This goes up to column 6 row 5.

        // For Java edition, this means the leftmost column and rightmost column are skipped
        // For Bedrock edition, this means the bottom two rows are skipped
        // This sadly is a sacrifice to make the menu look good on both editions
        // Later I will provide a menu type that can have two different layouts for Java and Bedrock

        // Limit to 6 columns and 5 rows
        if (column > 6) {
            column = 6;
        }
        if (row > 5) {
            row = 5;
        }

        if (menuType == PlayerUtil.MenuType.POCKET) {
            return column + row * 7;
        } else {
            // 1 + column *
            return 1 + column + row * 9;
        }
    }

    /**
     * Add a button to the menu by column and row
     * @param button the button to add
     * @param column the column to add the button to
     * @param row the row to add the button to
     * @param bedrockSafe if the menu should be bedrock safe. This limits the menu to 7 columns and 3 or 6 rows (depending on the menu size)
     */
    public void addButtonByColumnRow(MenuButton button, int column, int row, boolean bedrockSafe) {
        if (bedrockSafe) {
            PlayerUtil.MenuType menuType = PlayerUtil.getMenuType(player);
            addButton(button, slotByColumnRow(column, row, menuType));
        } else {
            addButton(button, slotByColumnRow(column, row));
        }
    }

    public void addButtonByColumnRow(MenuButton button, int column, int row) {
        addButtonByColumnRow(button, column, row, false);
    }




    public Menu build() {
        return new Menu(player, size, title, buttons, preventClose);
    }

}
