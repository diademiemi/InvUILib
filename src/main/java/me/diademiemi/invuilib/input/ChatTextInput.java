package me.diademiemi.invuilib.input;

import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class ChatTextInput {
    public static HashMap<Player, ChatTextInput> inputs = new HashMap<Player, ChatTextInput>();
    public Player player;
    public Object[] args;
    public ChatTextInput(Player player, Object... args) {
        this.player = player;
        this.args = args;
        addInput(this);
    }

    public ChatTextInput(Player player, String preamble, Object... args) {
        this(player, args);
        player.sendMessage(preamble);
    }

    public static HashMap<Player, ChatTextInput> getInputs() {
        return inputs;
    }

    public void addInput(ChatTextInput input) {
        inputs.put(input.getPlayer(), input);
    }

    public void remove() {
        inputs.remove(this.getPlayer());
    }

    public Player getPlayer() {
        return player;
    }

    public void onInput(String input) {
        inputs.remove(player);
        action(input);
    }

    public abstract void action(String input);

}
