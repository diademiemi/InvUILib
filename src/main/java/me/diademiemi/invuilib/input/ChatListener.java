package me.diademiemi.invuilib.input;

import me.diademiemi.invuilib.InvUILib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if (ChatTextInput.getInputs().containsKey(e.getPlayer())) {
            InvUILib.getPlugin().getServer().getScheduler()
                    .runTask(InvUILib.getPlugin(), () -> ChatTextInput.getInputs().get(e.getPlayer()).onInput(e.getMessage()));
            e.setCancelled(true);
        }
    }

}
