
package javaslice.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaslice.pizza.Pizza;
import javaslice.pizza.PizzaFactory;
import javaslice.pizza.Size;
import javaslice.util.Format;

/**
 * Menu
 * Manages the available items the user can buy
 * 
 * @author Andrian
 */
public class Menu {
    private Map<String, List<Size>> menu; // name -> list of sizes

    public Menu() {
        menu = new HashMap<>();

        // populate the menu with available items
        menu.put("Spinach", Arrays.asList(Size.S, Size.M, Size.L));
        menu.put("Pepperoni", Arrays.asList(Size.M, Size.L));
        menu.put("Hawaiian", Arrays.asList(Size.S, Size.M));
    }

    /**
     * Checks if the pizza is in the menu
     */
    public boolean hasPizza(Pizza pizza) {
        return pizza != null && hasName(pizza.getName()) && hasSize(pizza.getName(), pizza.getSize());
    }

    /**
     * Checks if the menu has a pizza with the given name
     */
    public boolean hasName(String name) {
        return menu.containsKey(name.trim());
    }

    /**
     * Checks if the menu has a pizza with the given name and size
     */
    public boolean hasSize(String name, Size size) {
        List<Size> sizes = menu.get(name.trim());
        return sizes != null && sizes.contains(size);
    }

    /**
     * Displays the menu
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        final int w = 15;

        // header (name + sizes)
        sb.append(Format.pad(Format.bold("Name"), w));
        for (Size size : Size.values()) {
            sb.append(Format.pad(Format.bold(size), w));
        }
        sb.append("\n");

        // rows
        for (String name : menu.keySet()) {
            sb.append(Format.pad(Format.bold(name), w));

            for (Size size : Size.values()) {
                Pizza pizza = PizzaFactory.create(name, size);
                sb.append(
                        Format.pad(hasPizza(pizza) ? Format.money(pizza.getPrice()) : Format.error("X"), w));
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
