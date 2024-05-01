package com.state;

import com.events.Event;

public class PaymentFailureState extends State {
    @Override
    public void entry() {
        System.out.println("Payment failed. Please try again.");
    }

    @Override
    public void exit() {
        System.out.println("Exiting payment failure state");
    }

    @Override
    public State handleEvent(Event event) {
        return null;
    }
}
