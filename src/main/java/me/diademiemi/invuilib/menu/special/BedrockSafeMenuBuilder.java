package me.diademiemi.invuilib.menu.special;

import me.diademiemi.invuilib.menu.MenuBuilder;
import me.diademiemi.invuilib.menu.MenuButton;
import me.diademiemi.invuilib.menu.MenuSize;
import me.diademiemi.invuilib.util.PlayerUtil;
import org.bukkit.entity.Player;

/*
    * This class is a special MenuBuilder that is safe for Bedrock edition players using the POCKET menu type.
    * This restricts the menu to 6 columns and 5 rows, and calculates the slot based on the column and row.
    * size should be either THREE_ROWS or SIX_ROWS.
 */
public class BedrockSafeMenuBuilder extends MenuBuilder {

    public BedrockSafeMenuBuilder(String title, MenuSize size, Player player) {
        super(title, size, player);
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

    public void addButtonByColumnRow(MenuButton button, int column, int row) {
        PlayerUtil.MenuType menuType = PlayerUtil.getMenuType(player);

        addButton(button, slotByColumnRow(column, row, menuType));
    }

}
