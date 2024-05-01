package com.raspberry;

import com.diozero.api.I2CConstants;
import com.diozero.devices.BMx280;
import com.diozero.devices.ThermometerInterface;
import com.diozero.util.SleepUtil;

public class Main {
    public static void main(String[] args) {

        int i2c_bus = I2CConstants.CONTROLLER_1;

        System.out.println("--- USING I2C-" + i2c_bus + " ---");
        try (BMx280 bmx280 = BMx280.I2CBuilder.builder(i2c_bus).setAddress(0x77).build()) {
            while (true) {
                bmx280.waitDataAvailable(10, 5);
                float[] tph = bmx280.getValues();
                System.out.format("Temperature: %.2f C (%.2f F), Pressure: %.2f hPa", Float.valueOf(tph[0]),
                        Float.valueOf(ThermometerInterface.celsiusToFahrenheit(tph[0])), Float.valueOf(tph[1]));
                if (bmx280.getModel() == BMx280.Model.BME280) {
                    System.out.format(", Relative Humidity: %.2f%% RH", Float.valueOf(tph[2]));
                }
                System.out.println();

                SleepUtil.sleepSeconds(1);
            }
        } catch (Exception e) {
            System.out.println("Device not found");
        }
    }
}
