package me.diademiemi.invuilib.util;

import org.bukkit.entity.Player;
import org.geysermc.api.util.UiProfile;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.connection.GeyserConnection;

public class PlayerUtil {
    public enum MenuType {
        CLASSIC,
        POCKET
    }

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
