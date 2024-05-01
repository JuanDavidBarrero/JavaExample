package com;

import com.controller.FSMController;
import com.events.Event;


public class App {
    public static void main(String[] args) {
        FSMController controller = new FSMController();

        controller.handleEvent(Event.START_SHOPPING);
        controller.handleEvent(Event.ADD_TO_CART);
        controller.handleEvent(Event.ADD_TO_CART);
        controller.handleEvent(Event.ADD_TO_CART);
        controller.handleEvent(Event.PROCEED_TO_PAYMENT);
        controller.handleEvent(Event.MAKE_PAYMENT);
    }
}