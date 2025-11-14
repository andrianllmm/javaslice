package javaslice.pizza;

/**
 * Hawaiian
 * Pizza with Hawaiian toppings
 * 
 * @author Andrian
 */
public class Hawaiian extends Pizza {

    public Hawaiian(Size size) {
        super("Hawaiian", size);
    }

    public Hawaiian() {
        super("Hawaiian");
    }

    @Override
    public double getPrice() {
        switch (size) {
            case S:
                return 130;
            case M:
                return 210;
            case L:
                return 300;
            default:
                return 0;
        }
    }
}