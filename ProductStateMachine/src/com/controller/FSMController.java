package com.controller;

import com.events.Event;
import com.state.*;

public class FSMController {
    private State currentState;

    public FSMController() {
        currentState = new StartState();
        currentState.entry();
    }

    public void changeState(State nextState) {
        currentState.exit();
        currentState = nextState;
        currentState.entry();
    }

    public void handleEvent(Event event) {
        State nextState = currentState.handleEvent(event);
        if (nextState != null && nextState != currentState) { 
            changeState(nextState);
        } else {
            return;
        }
    }
    
}