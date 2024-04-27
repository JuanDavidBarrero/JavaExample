package com.raspberry;

import com.diozero.api.I2CDevice;
import com.diozero.devices.oled.SSD1306;
import com.diozero.devices.oled.SsdOledCommunicationChannel;
import com.diozero.devices.oled.SSD1306.Height;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        I2CDevice device = new I2CDevice(1, 0x3c);

        SsdOledCommunicationChannel channel = new SsdOledCommunicationChannel.I2cCommunicationChannel(device);

        try (SSD1306 oled = new SSD1306(channel, Height.SHORT)) {

            int width = oled.getWidth();
            int height = oled.getHeight();

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2d = image.createGraphics();

            g2d.setColor(Color.white);
            g2d.setBackground(Color.black);

            Font titleFont = new Font("Monospaced", Font.PLAIN, 16);
            g2d.setFont(titleFont);
            g2d.drawString("Hades", 0, 20);

            oled.display(image);

            System.out.println("Showing title");
            Thread.sleep(5000);

            int counter = 0;
            while (true) {
                g2d.clearRect(0, 20, width, 10); 
                g2d.drawString("Contador: " + counter, 0, 30);

                oled.display(image);

                counter++;

                Thread.sleep(1000); 
            }

        } catch (Exception e) {
            System.out.println("Device not found");
        }
    }
}

