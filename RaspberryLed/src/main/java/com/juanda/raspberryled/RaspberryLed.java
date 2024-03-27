package com.juanda.raspberryled;

import com.diozero.api.GpioPullUpDown;
import com.diozero.devices.Button;
import com.diozero.devices.LED;

public class RaspberryLed {

    private static final int LED_PIN = 20;
    private static final int BUTTON_PIN = 26;
    private static final int LED2_PIN = 16;

    public static void main(String[] args) {

        try (LED led = new LED(LED_PIN); Button button = new Button(BUTTON_PIN, GpioPullUpDown.PULL_UP); LED led2 = new LED(LED2_PIN)) {

            Thread buttonThread = new Thread(() -> {
                try {
                    button.whenPressed(nanoTime -> led2.on());
                    button.whenReleased(nanoTime -> led2.off());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            buttonThread.start();

            while (true) {
                button.whenPressed(nanoTime -> led2.on());
                button.whenReleased(nanoTime -> led2.off());

                led.on();

                Thread.sleep(1000);

                led.off();

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("Error initializing pins");
        }
    }
}
