package javaslice.util;

/**
 * Color
 * ANSI Colors for the terminal
 * 
 * @author Andrian
 */
public class Color {
    // Resets the color
    public static final String RESET = "\u001B[0m";

    // Foregrounds
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String DIM = "\u001B[2m";

    // Backgrounds
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";

    // Styles
    public static final String BOLD = "\u001B[1m";

    // Helper
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }
}
