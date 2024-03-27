package com.juanda.raspberryinput;

import com.diozero.api.GpioEventTrigger;
import com.diozero.api.GpioPullUpDown;
import com.diozero.api.RuntimeIOException;
import com.diozero.api.DigitalInputDevice;

public class RaspberryInput {

    private static final int BUTTON_PIN = 26;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try (DigitalInputDevice input = new DigitalInputDevice(BUTTON_PIN, GpioPullUpDown.PULL_UP, GpioEventTrigger.BOTH)) {

            while (true) {
                if (!input.isActive()) {
                    System.out.println("Button is High " + input.getValue());
                } else {
                    System.out.println("Button in state " + input.getValue());
                }
                Thread.sleep(100); // Delay of 100 milliseconds
            }

        } catch (RuntimeIOException ioe) {
            System.out.print("Something went wrong");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException occurred: " + e.getMessage());
        }
    }
}
