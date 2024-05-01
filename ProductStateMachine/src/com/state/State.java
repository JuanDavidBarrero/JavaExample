package com.state;

import com.events.Event;

public abstract class State {
    public abstract void entry();
    public abstract void exit();
    public abstract State handleEvent(Event event);
}