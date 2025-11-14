
package javaslice.manager;

import java.util.HashMap;
import java.util.Map;

import javaslice.pizza.Pizza;
import javaslice.util.Format;

/**
 * Cart
 * Manages the items the user wants to buy
 * 
 * @author Andrian
 */
public class Cart {
    private Map<Pizza, Integer> items = new HashMap<>(); // pizza -> quantity
    private boolean checkedOut = false; // true if the order has been paid

    /**
     * Adds n items to the cart
     */
    public void addItem(Pizza pizza, int qty) throws IllegalArgumentException {
        if (qty <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0.");

        // merge quantity if already in cart
        items.put(pizza, items.getOrDefault(pizza, 0) + qty);
    }

    /**
     * Adds 1 item to the cart
     */
    public void addItem(Pizza pizza) {
        addItem(pizza, 1);
    }

    /**
     * Removes n items from the cart
     */
    public void removeItem(Pizza pizza, int qty) throws IllegalArgumentException {
        if (qty <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0.");

        int currentQty = items.get(pizza);
        if (qty > currentQty) {
            throw new IllegalArgumentException("Quantity must be less than current.");
        }

        if (qty == currentQty) {
            items.remove(pizza); // remove the pizza entirely
        } else {
            items.put(pizza, currentQty - qty); // decrease the quantity
        }
    }

    /**
     * Removes 1 item from the cart
     */
    public void removeItem(Pizza pizza) {
        removeItem(pizza, 1);
    }

    /**
     * Calculates the total price of the items in the cart
     */
    public double getTotal() {
        double total = 0;
        // sum the prices of all the pizzas in the cart
        for (Map.Entry<Pizza, Integer> entry : items.entrySet()) {
            // price x quantity
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Gets the quantity of a pizza in the cart
     */
    public int getQty(Pizza pizza) {
        return items.get(pizza);
    }

    /**
     * Checks if the cart has a pizza
     */
    public boolean hasPizza(Pizza pizza) {
        return items.containsKey(pizza);
    }

    /**
     * Checks if the order has been paid
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * Marks the order as paid
     */
    public void markAsCheckedOut() {
        this.checkedOut = true;
    }

    /**
     * Checks if the cart is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Displays the cart
     */
    public String display() {
        if (items.isEmpty())
            return "Cart is empty";

        StringBuilder sb = new StringBuilder();

        // list of pizzas (with quantity and total price)
        for (Map.Entry<Pizza, Integer> entry : items.entrySet()) {
            Pizza pizza = entry.getKey();
            int qty = entry.getValue();
            double subtotal = pizza.getPrice() * qty;
            sb.append(String.format("%s - %dx - %s%n", pizza, qty, Format.money(subtotal)));
        }

        // total
        String totalLine = "Total: " + Format.money(getTotal());
        sb.append("─".repeat(totalLine.length())).append("\n");
        sb.append(Format.bold(totalLine)).append("\n");

        return sb.toString();
    }
}
