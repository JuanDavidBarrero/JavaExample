import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    private static ServerSocket server;
    private static int port = 5001;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        server = new ServerSocket(port);

        while(true){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ReceiveData = new ObjectInputStream(socket.getInputStream());
            String message = (String) ReceiveData.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream DataTx = new ObjectOutputStream(socket.getOutputStream());
            DataTx.writeObject("Hi Client "+message);
            ReceiveData.close();
            DataTx.close();
            socket.close();
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        server.close();

    }
}
