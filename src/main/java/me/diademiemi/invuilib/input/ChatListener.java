package me.diademiemi.invuilib.input;

import me.diademiemi.invuilib.InvUILib;
import me.diademiemi.invuilib.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if (ChatTextInput.getInputs().containsKey(e.getPlayer())) {
            InvUILib.getPlugin().getServer().getScheduler()
                    .runTask(InvUILib.getPlugin(), () -> ChatTextInput.getInputs().get(e.getPlayer()).onInput(e.getMessage()));
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerQUit(PlayerQuitEvent e) {
        ChatTextInput input = ChatTextInput.getInputs().get(e.getPlayer());
        if (input != null) {
            input.remove();
        }
    }
}
