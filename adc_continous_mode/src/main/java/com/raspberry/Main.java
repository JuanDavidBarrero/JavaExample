package com.raspberry;

import com.diozero.api.AnalogInputDevice;
import com.diozero.api.DigitalInputDevice;
import com.diozero.api.GpioEventTrigger;
import com.diozero.api.GpioPullUpDown;
import com.diozero.devices.Ads1x15;
import com.diozero.util.SleepUtil;

public class Main {

    static final int channel = 0;

    public static void main(String[] args) {

        int controller = 1;

        int adc_ready_gpio = 17;

        try (Ads1x15 adc = new Ads1x15(controller, Ads1x15.Address.GND, Ads1x15.PgaConfig._4096MV,
                Ads1x15.Ads1115DataRate._8HZ);

                AnalogInputDevice ain0 = new AnalogInputDevice(adc, channel);

                DigitalInputDevice adc_ready_pin = new DigitalInputDevice(adc_ready_gpio, GpioPullUpDown.PULL_UP,
                        GpioEventTrigger.BOTH)) {

            adc.setContinousMode(adc_ready_pin, ain0.getGpio(),
                    reading -> System.out.format("Callback - Channel #%d : %.2f%% (%.2fv)%n", channel, reading,
                            ain0.convertToScaledValue(reading)));

            SleepUtil.sleepSeconds(10);

        } catch (Exception e) {
            System.out.println("Device not found");
        }
    }
}