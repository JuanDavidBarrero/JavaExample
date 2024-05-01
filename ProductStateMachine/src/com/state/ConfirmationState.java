package com.state; 

import com.events.Event;

public class ConfirmationState extends State {
    @Override
    public void entry() {
        System.out.println("Your purchase has been confirmed. Thank you for shopping with us!");
    }

    @Override
    public void exit() {
        System.out.println("Exiting confirmation state");
    }

    @Override
    public State handleEvent(Event event) {
        return null;
    }
}