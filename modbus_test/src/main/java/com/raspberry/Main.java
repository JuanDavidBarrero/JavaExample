package com.raspberry;

import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.procimg.Register;   
// import com.ghgande.j2mod.modbus.procimg.InputRegister;  // this is por Input registers   
// import com.ghgande.j2mod.modbus.util.BitVector;   // this one is for coil
import com.ghgande.j2mod.modbus.util.SerialParameters;

public class Main {
    public static void main(String[] args) {
        SerialParameters serial = new SerialParameters();
        serial.setPortName("ttyUSB0");
        serial.setBaudRate(115200);
        serial.setDatabits(8);
        serial.setParity("None");
        serial.setStopbits(1);
        serial.setEncoding("rtu");
        serial.setEcho(false);

        ModbusSerialMaster master = new ModbusSerialMaster(serial);

        try {
            master.connect();
            System.out.println("The modbus channel is open");

            int slaveId = 1;
            int address = 0;
            int quantity = 5;

            while (true) {
                Register[] values = master.readMultipleRegisters(slaveId, address, quantity);

                // InputRegister[] values = master.readInputRegisters(slaveId, address, quantity);

                // BitVector values = master.readCoils(slaveId, address, quantity);

                for (int i = 0; i < values.length; i++) {
                    System.out.println("Registro " + (address + i) + ": " + values[i]);
                }
                System.out.println("\n\n");
                Thread.sleep(2000); // Espera 5 segundos antes de la próxima lectura
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            master.disconnect();
        }
    }
}
