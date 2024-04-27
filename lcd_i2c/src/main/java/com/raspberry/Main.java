package com.raspberry;

import com.diozero.api.I2CConstants;
import com.diozero.devices.HD44780Lcd;
import com.diozero.devices.LcdConnection;
import com.diozero.util.SleepUtil;

public class Main {
    public static void main(String[] args) {
        int device_address = LcdConnection.PCF8574LcdConnection.DEFAULT_DEVICE_ADDRESS;
        int controller = I2CConstants.CONTROLLER_1;

        try (LcdConnection lcdConnection = new LcdConnection.PCF8574LcdConnection(controller, device_address);
                HD44780Lcd lcd = new HD44780Lcd(lcdConnection, 20, 4)) {

            byte[] space_invader = new byte[] { 0x00, 0x0e, 0x15, 0x1f, 0x0a, 0x04, 0x0a, 0x11 };
            byte[] smilie = new byte[] { 0x00, 0x00, 0x0a, 0x00, 0x00, 0x11, 0x0e, 0x00 };
            byte[] frownie = new byte[] { 0x00, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x0e, 0x11 };
            lcd.createChar(0, space_invader);
            lcd.createChar(1, smilie);
            lcd.createChar(2, frownie);

            lcd.clear();

            lcd.setText(0, "Hola Mundo");

            lcd.setText(1, "" + (char) 0 + (char) 1 + (char) 2);

            int contador = 0;

            while (true) {

                lcd.setText(2, "Contador: " + contador);

                contador++;

                SleepUtil.sleepSeconds(1);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
