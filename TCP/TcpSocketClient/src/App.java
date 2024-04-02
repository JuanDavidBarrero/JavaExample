import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream DataTx = null;
        ObjectInputStream DataRx = null;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter 'exit' to terminate.");
            while (true) {
                System.out.print("Enter a message to send to the server: ");
                String messageToSend = scanner.nextLine();

                socket = new Socket(host.getHostName(), 5001);
                DataTx = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Sending request to Socket Server");
                DataTx.writeObject(messageToSend);

                DataRx = new ObjectInputStream(socket.getInputStream());
                String message = (String) DataRx.readObject();
                System.out.println("Message from server: " + message);

                if (messageToSend.equalsIgnoreCase("exit")) {
                    System.out.println("Closing connection.");
                    break;
                }

                DataRx.close();
                DataTx.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
