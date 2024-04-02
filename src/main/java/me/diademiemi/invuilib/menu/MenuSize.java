package me.diademiemi.invuilib.menu;

/**
 * Enum for the size of the GUI
 * Only THREE_ROWS and SIX_ROWS are stable on Bedrock Edition
 */
public enum MenuSize {
    /**
     * Half row menu size.
     */
    HALF_ROW(5),
    /**
     * One row menu size.
     */
    ONE_ROW(9),
    /**
     * Two rows menu size.
     */
    TWO_ROWS(18),
    /**
     * The Three rows.
     */
// Stable on Bedrock Edition
    THREE_ROWS(27),
    /**
     * Four rows menu size.
     */
    FOUR_ROWS(36),
    /**
     * Five rows menu size.
     */
    FIVE_ROWS(45),
    /**
     * The Six rows.
     */
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
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
