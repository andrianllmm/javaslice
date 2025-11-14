
package javaslice.util;

import static javaslice.Config.LOCALE;
import static javaslice.util.Color.*;

import java.text.NumberFormat;

/**
 * Format
 * Formats text to be displayed to the user
 * 
 * @author Andrian
 */
public class Format {
    /**
     * Formats a number as currency
     */
    public static String money(double amount) {
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(LOCALE);
        return currFmt.format(amount);
    }

    public static String bold(Object s) {
        return colorize(s.toString(), BOLD);
    }

    public static String dim(Object s) {
        return colorize(s.toString(), DIM);
    }

    public static String error(Object s) {
        return colorize(s.toString(), RED);
    }

    public static String success(Object s) {
        return colorize(s.toString(), GREEN);
    }

    public static String accent(Object s) {
        return colorize(s.toString(), YELLOW);
    }

    /**
     * Pads a string to a certain width (mostly used for tables)
     */
    public static String pad(Object o, int w) {
        String s = o.toString();
        String plain = s.replaceAll("\u001B\\[[;\\d]*m", ""); // remove ANSI codes
        int pad = Math.max(0, w - plain.length());
        return s + " ".repeat(pad);
    }
}
