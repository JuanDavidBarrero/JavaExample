import java.net.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        DatagramPacket sendPacket;
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(1000);
        Scanner input = new Scanner(System.in);

        System.out.println("Enter 'exit' to terminate.");

        while (true) {
            String cmd = input.nextLine();
            byte[] sendData = cmd.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("127.0.0.1"), 5001);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response from server: " + response);

            if (response.equals("exit")) {
                input.close();
                clientSocket.close();
                break;
            }
        }
    }
}
