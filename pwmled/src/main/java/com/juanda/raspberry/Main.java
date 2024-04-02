package com.juanda.raspberry;

import com.diozero.devices.PwmLed;
import com.diozero.util.Diozero;
import com.diozero.util.SleepUtil;

public class Main {

    private static final int pin = 12;
    private static final float delay = 0.1f; 
    public static void main(String[] args) {

        System.out.println("PWM test Raspberry pi");

        try (PwmLed led = new PwmLed(pin)){

            while (true) {
                System.out.println("Led 0%");
                led.setValue(0f); 
                SleepUtil.sleepSeconds(delay);

                for (float i = 0; i <= 1; i += 0.1f) {
                    System.out.println("Led " + (int)(i * 100) + "%");
                    led.setValue(i);
                    SleepUtil.sleepSeconds(delay);
                }

                System.out.println("Led 100%");
                led.setValue(1f); 

                for (float i = 1; i >= 0; i -= 0.1f) {
                    System.out.println("Led " + (int)(i * 100) + "%");
                    led.setValue(i);
                    SleepUtil.sleepSeconds(delay);
                }

                System.out.println("Led 0%");
                led.setValue(0f); 
            }
        } catch (Exception e) {
            Diozero.shutdown();
        }
    }
}
