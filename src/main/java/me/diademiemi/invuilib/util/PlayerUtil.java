package me.diademiemi.invuilib.util;

import org.bukkit.entity.Player;
import org.geysermc.api.util.UiProfile;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.connection.GeyserConnection;

/**
 * The type Player util.
 */
public class PlayerUtil {
    /**
     * The enum Menu type.
     */
    public enum MenuType {
        /**
         * Classic menu type.
         */
        CLASSIC,
        /**
         * Pocket menu type.
         */
        POCKET
    }

    /**
     * Gets menu type.
     *
     * @param player the player
     * @return the menu type
     */
    public static MenuType getMenuType(Player player) {
        GeyserConnection connection = GeyserApi.api().connectionByUuid(player.getUniqueId());

        if (connection == null) {
            return MenuType.CLASSIC;
        } else if (connection.uiProfile() == UiProfile.CLASSIC) {
            return MenuType.CLASSIC;
        } else if (connection.uiProfile() == UiProfile.POCKET) {
            return MenuType.POCKET;
        } else {
            return MenuType.CLASSIC;
        }
    }
}
