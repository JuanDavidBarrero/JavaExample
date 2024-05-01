package com.state;
import com.events.Event;

public class ProductSelectionState extends State {
    @Override
    public void entry() {
        System.out.println("Select the products you want to buy.");
    }

    @Override
    public void exit() {
        System.out.println("Exiting product selection state");
    }

    @Override
    public State handleEvent(Event event) {
        switch (event) {
            case ADD_TO_CART:
                System.out.println("Product added to the card");
                return this;
            case PROCEED_TO_PAYMENT:
                return new PaymentState();
            default:
                return null;
        }
    }
}