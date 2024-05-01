package com.state;
import com.events.Event;

public class StartState extends State {
    @Override
    public void entry() {
        System.out.println("Welcome to our online store. Start your shopping.");
    }

    @Override
    public void exit() {
        System.out.println("Exiting start state");
    }

    @Override
    public State handleEvent(Event event) {
        if (event == Event.START_SHOPPING) {
            return new ProductSelectionState();
        }
        return null;
    }
}