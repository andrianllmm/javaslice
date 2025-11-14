package javaslice.util;

import static javaslice.Config.*;

import java.util.Scanner;

import javaslice.manager.Action;
import javaslice.pizza.Pizza;
import javaslice.pizza.PizzaFactory;
import javaslice.pizza.Size;

/**
 * InputHandler
 * Handles user input (wrapper for Scanner)
 * 
 * @author Andrian
 */
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PROMPT_SYMBOL = "▶"; // used as an indicator for the prompt

    /**
     * Prompt the user for input with a message
     */
    public static String prompt(String message) {
        System.out.print(String.format("%s %s ",
                message,
                Format.accent(PROMPT_SYMBOL)));
        return scanner.nextLine().trim();
    }

    /**
     * Prompt the user for input with a default value
     */
    public static String promptOrDefault(String message, String defaultValue) {
        System.out.print(String.format("%s %s %s ",
                message,
                Format.dim("[" + defaultValue + "]"),
                Format.accent(PROMPT_SYMBOL)));
        String input = scanner.nextLine().trim();
        if (input.isEmpty())
            return defaultValue;
        return input;
    }

    /**
     * Prompt the user for a valid number
     */
    public static double promptNum(String message) {
        while (true) {
            String input = prompt(message);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(Format.error("Please enter a valid number"));
            }
        }
    }

    /**
     * Prompt the user for a valid number with a default value
     */
    public static double promptNumOrDefault(String message, double defaultValue) {
        while (true) {
            String input = promptOrDefault(message, String.valueOf(defaultValue));
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(Format.error("Please enter a valid number"));
            }
        }
    }

    /**
     * Prompt the user for a boolean yes/no answer
     */
    public static boolean promptYesNo(String message) {
        while (true) {
            String input = promptOrDefault(message, "y");
            if (input.equalsIgnoreCase("y"))
                return true;
            if (input.equalsIgnoreCase("n"))
                return false;
            System.out.println("Please enter 'y' or 'n'");
        }
    }

    /**
     * Prompt the user for an action
     */
    public static Action promptForAction() {
        // show actions
        int cols = 3;
        Action[] actions = Action.values();

        for (int i = 0; i < actions.length; i++) {
            String label = Format.bold(Format.accent(actions[i].getCode()));
            label += "  " + actions[i].getName();
            System.out.print(Format.pad(label, 15));
            if ((i + 1) % cols == 0)
                System.out.println();
        }
        if (actions.length % cols != 0)
            System.out.println();

        // prompt for a valid action
        while (true) {
            try {
                String actionCode = prompt("");
                return Action.of(actionCode);
            } catch (IllegalArgumentException e) {
                System.out.println(Format.error("Invalid choice"));
            }
        }
    }

    /**
     * Prompt the user for a pizza (flavor + size)
     */
    public static Pizza promptForPizza() {
        // prompt until a valid pizza is selected
        while (true) {
            String pizzaName = prompt("Pick a pizza");
            String sizeName = promptOrDefault("Pick a size", DEFAULT_SIZE.getAbbr());

            try {
                return PizzaFactory.create(pizzaName, Size.of(sizeName));
            } catch (IllegalArgumentException e) {
                System.out.println(Format.error("Invalid pizza"));
            }
        }
    }

    /**
     * Prompt for a quantity with a max (negative means no max)
     */
    public static int promptForQty(int max) {
        // prompt until a valid quantity is selected
        while (true) {
            int qty = (int) promptNumOrDefault("Quantity", 1);
            if (qty <= 0 || (max > 0 && qty > max)) {
                System.out.println(Format.error("Invalid quantity"));
                continue;
            }
            return qty;
        }
    }

    /**
     * Prompt for a quantity with a max
     */
    public static int promptForQty() {
        return promptForQty(-1);
    }

    /**
     * Close the input handler
     */
    public static void close() {
        scanner.close();
    }
}
