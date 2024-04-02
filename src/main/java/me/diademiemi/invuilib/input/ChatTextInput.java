package me.diademiemi.invuilib.input;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * The type Chat text input.
 */
public abstract class ChatTextInput {
    /**
     * The constant inputs.
     */
    public static HashMap<Player, ChatTextInput> inputs = new HashMap<Player, ChatTextInput>();
    /**
     * The Player.
     */
    public Player player;
    /**
     * The Args.
     */
    public Object[] args;

    /**
     * Instantiates a new Chat text input.
     *
     * @param player the player
     * @param args   the args
     */
    public ChatTextInput(Player player, Object... args) {
        this.player = player;
        this.args = args;
        addInput(this);
    }

    /**
     * Instantiates a new Chat text input.
     *
     * @param player   the player
     * @param preamble the preamble
     * @param args     the args
     */
    public ChatTextInput(Player player, String preamble, Object... args) {
        this(player, args);
        player.sendMessage(preamble);
    }

    /**
     * Gets inputs.
     *
     * @return the inputs
     */
    public static HashMap<Player, ChatTextInput> getInputs() {
        return inputs;
    }

    /**
     * Add input.
     *
     * @param input the input
     */
    public void addInput(ChatTextInput input) {
        inputs.put(input.getPlayer(), input);
    }

    /**
     * Remove.
     */
    public void remove() {
        inputs.remove(this.getPlayer());
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * On input.
     *
     * @param input the input
     */
    public void onInput(String input) {
        inputs.remove(player);
        action(input);
    }

    /**
     * Action.
     *
     * @param input the input
     */
    public abstract void action(String input);

}
