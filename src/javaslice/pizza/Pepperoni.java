package javaslice.pizza;

/**
 * Pepperoni
 * Pizza with pepperoni toppings
 * 
 * @author Andrian
 */
public class Pepperoni extends Pizza {

    public Pepperoni(Size size) {
        super("Pepperoni", size);
    }

    public Pepperoni() {
        super("Pepperoni");
    }

    @Override
    public double getPrice() {
        switch (size) {
            case S:
                return 150;
            case M:
                return 250;
            case L:
                return 250;
            default:
                return 0;
        }
    }
}