
package javaslice.pizza;

/**
 * Spinach
 * Pizza with spinach toppings
 * 
 * @author Andrian
 */
public class Spinach extends Pizza {

    public Spinach(Size size) {
        super("Spinach", size);
    }

    public Spinach() {
        super("Spinach");
    }

    @Override
    public double getPrice() {
        switch (size) {
            case S:
                return 120;
            case M:
                return 200;
            case L:
                return 280;
            default:
                return 0;
        }
    }
}