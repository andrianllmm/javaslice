package javaslice;

import java.util.Locale;

import javaslice.pizza.Size;

/**
 * Config
 * Global variables for the program; "settings"
 * 
 * @author Andrianm
 */
public class Config {
    public static final Locale LOCALE = new Locale("en", "PH"); // locale for currency
    public static final Size DEFAULT_SIZE = Size.M; // default size for pizzas

    private Config() {
    } // prevent instantiation
}
