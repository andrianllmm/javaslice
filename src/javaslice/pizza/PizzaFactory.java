package javaslice.pizza;

import static javaslice.Config.DEFAULT_SIZE;

/**
 * PizzaFactory
 * Creates concrete pizzas from a name and size
 * 
 * @author Andrian
 */
public class PizzaFactory {
    public static Pizza create(String name, Size size) throws IllegalArgumentException {
        name = name.trim().toLowerCase();

        if (name.equals("spinach"))
            return new Spinach(size);
        if (name.equals("pepperoni"))
            return new Pepperoni(size);
        if (name.equals("hawaiian"))
            return new Hawaiian(size);

        throw new IllegalArgumentException("Unknown pizza: " + name);
    }

    public static Pizza create(String name) {
        return create(name, DEFAULT_SIZE);
    }
}
