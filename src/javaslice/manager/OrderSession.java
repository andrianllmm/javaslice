package javaslice.manager;

import static javaslice.util.InputHandler.*;

import javaslice.util.Format;
import javaslice.manager.OrderSession;
import javaslice.pizza.Pizza;

/**
 * OrderSession
 * Manages an order session from a user
 * 
 * @author Andrian
 */
public class OrderSession {
    Cart cart;
    Menu menu;

    /**
     * Initializes the order session
     */
    public OrderSession(Cart cart, Menu menu) {
        this.cart = cart;
        this.menu = menu;
    }

    /**
     * Start the order session
     */
    public void start() {
        showWelcome();

        while (true) {
            Action action = promptForAction();
            System.out.println();

            executeAction(action);
            System.out.println();
        }
    }

    /**
     * Execute an action
     */
    private void executeAction(Action action) {
        // execute the action
        switch (action) {
            case VIEW_MENU:
                System.out.println(menu.display());
                break;
            case ADD:
                add();
                break;
            case REMOVE:
                remove();
                break;
            case VIEW_CART:
                System.out.println(cart.display());
                break;
            case CHECKOUT:
                checkout();
                break;
            case QUIT:
                System.out.println(Format.success("Thanks for stopping by!"));
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Add an item to the cart
     */
    private void add() {
        System.out.println(menu.display());

        // get pizza and quantity to add
        Pizza pizza = promptForPizza();
        if (!menu.hasPizza(pizza)) {
            System.out.println(Format.error("That's not in the menu today"));
            return;
        }
        int qty = promptForQty();

        cart.addItem(pizza, qty);
    }

    /**
     * Remove an item from the cart
     */
    private void remove() {
        if (cart.isEmpty()) {
            System.out.println(Format.error("Nothing to remove"));
            return;
        }
        System.out.println(cart.display());

        // get pizza and quantity to remove
        Pizza pizza = promptForPizza();
        if (!cart.hasPizza(pizza)) {
            System.out.println(Format.error("That's not in your cart"));
            return;
        }
        int qty = promptForQty(cart.getQty(pizza));

        cart.removeItem(pizza, qty);
    }

    /**
     * Checkout the cart
     */
    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println(Format.error("Nothing to checkout"));
            return;
        }

        // initialize the transaction
        Transaction transaction = new Transaction(cart);

        // prompt until a valid amount is entered
        while (true) {
            int cancel = -1;
            double payment = promptNum(String.format("Pay %s [%d to cancel]", Format.money(cart.getTotal()), cancel));
            if (payment == cancel)
                return;

            try {
                transaction.pay(payment);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Format.error("Insufficient amount"));
            }
        }

        // give the change
        System.out.println("Here's your " + Format.money(transaction.getChange()) + " change");

        // ask if the user wants a receipt
        boolean receipt = promptYesNo("Would you like a receipt?");
        if (receipt)
            System.out.println("\n" + transaction.generateReceipt());

        // finish the order
        cart.markAsCheckedOut();
        System.out.println(Format.success("Thank you for shopping!"));
        System.exit(0);
    }

    /**
     * Welcome message when starting the order session
     */
    private void showWelcome() {
        System.out.println();
        System.out.println(Format.bold("🍕 JavaSlice 🍕"));
        System.out.println();
    }
}
