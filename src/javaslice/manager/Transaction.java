
package javaslice.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javaslice.util.Format;

/**
 * PaymentService
 * Manages paying orders and generating receipts
 * 
 * @author Andrian
 */
public class Transaction {
    private Cart cart; // the cart to pay
    private boolean paid = false; // true if the order has been paid
    private double amount; // amount to pay

    public Transaction(Cart cart) {
        this.cart = cart;
    }

    /**
     * Pay the order with a valid amount
     */
    public double pay(double amount) throws IllegalStateException, IllegalArgumentException {
        // check if the order has already been paid
        if (isPaid())
            throw new IllegalStateException("Order is already paid.");

        double total = cart.getTotal();
        if (amount < total)
            throw new IllegalArgumentException("Amount must be greater than total");

        paid = true;
        this.amount = amount;

        // return change
        return amount - total;
    }

    /**
     * Generate a receipt (summary) for the order
     */
    public String generateReceipt() throws IllegalStateException {
        if (!isPaid())
            throw new IllegalStateException("Order is not yet paid.");

        StringBuilder sb = new StringBuilder();
        // get the current date and time
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        sb.append("========== Receipt ==========\n");
        sb.append("Date: ").append(timestamp).append("\n\n");
        sb.append(cart.display());
        sb.append("Paid: ").append(Format.money(getAmount())).append("\n");
        sb.append("Change: ").append(Format.money(getChange())).append("\n");
        sb.append("=============================\n");

        return sb.toString();
    }

    public boolean isPaid() {
        return paid;
    }

    public double getChange() {
        return getAmount() - getTotal();
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public double getAmount() {
        return amount;
    }
}