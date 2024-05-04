package com.raspberry;

import com.diozero.api.AnalogInputDevice;
import com.diozero.devices.Ads1x15;
import com.diozero.util.SleepUtil;

public class Main {

    static float adcread0;
    static float adcread3;
    static float adcread_scaled3;

    public static void main(String[] args) {

        int controller = 1;

        try (Ads1x15 adc = new Ads1x15(controller,Ads1x15.Address.GND,Ads1x15.PgaConfig._6144MV,Ads1x15.Ads1115DataRate._860HZ);
            AnalogInputDevice ain0 = new AnalogInputDevice(adc, 0);
            AnalogInputDevice ain3 = new AnalogInputDevice(adc, 3);
        ) {

            System.out.println("Ranga is -> " + ain0.getRange());


            while (true) {

                adcread0 = ain0.getUnscaledValue();

                adcread3 = ain3.getUnscaledValue();
                adcread0 = ain3.convertToScaledValue(adcread0);
                
                adcread_scaled3 = ain3.convertToScaledValue(adcread3);


                System.out.printf("The value from channel %d is %f\n", 0, adcread0);
                System.out.printf("The value from channel %d is %f and scaled is %f\n", 3, adcread3, adcread_scaled3);

                SleepUtil.sleepMillis(1000);
                
            }

            

           
        } catch (Exception e) {
            System.out.println("Device not found");
        }
    }
}