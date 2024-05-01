package com.state;
import com.events.Event;

public class PaymentState extends State {
    @Override
    public void entry() {
        System.out.println("Enter payment details.");
    }

    @Override
    public void exit() {
        System.out.println("Exiting payment state");
    }

    @Override
    public State handleEvent(Event event) {
        switch (event) {
            case MAKE_PAYMENT:
                return new ConfirmationState();
            default:
                return null;
        }
    }
}