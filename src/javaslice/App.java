
package javaslice;

import javaslice.manager.OrderSession;
import javaslice.manager.Cart;
import javaslice.manager.Menu;

/**
 * App
 * Entry point of the application
 * 
 * @author Andrian
 */
public class App {
    public static void main(String[] args) {
        // Create the order session
        Cart cart = new Cart();
        Menu menu = new Menu();
        OrderSession orderSession = new OrderSession(cart, menu);

        // Start the order session
        orderSession.start();
    }
}
