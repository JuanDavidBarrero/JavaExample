import java.net.*;
import java.sql.Timestamp;

public class App {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt("5001"));
        System.out.println("Server Started. Listening for Clients on port 5001" + "...");

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket;

        while (true) {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(
                    "[" + timestamp.toString() + " ,IP: " + IPAddress + " ,Port: " + port + "]  " + clientMessage);

            if (clientMessage.trim().equalsIgnoreCase("exit")) {
                System.out.println("Client sent 'exit'. Sending 'exit' to client and closing server.");
                byte[] sendData = "exit".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                serverSocket.close();
                break;
            } else {
                String serverResponse = "Message recived";
                byte[] sendData = serverResponse.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }
    }
}
