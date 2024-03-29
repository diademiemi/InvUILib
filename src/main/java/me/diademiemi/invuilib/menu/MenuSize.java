package me.diademiemi.invuilib.menu;

public enum MenuSize {
    HALF_ROW(5),
    ONE_ROW(9),
    TWO_ROWS(18),
    // Stable on Bedrock Edition
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(45),
    // Stable on Bedrock Edition
    SIX_ROWS(54);

    private final int size;

    /**
     * @param size the size of the GUI
     */
    MenuSize(int size) {
        this.size = size;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
