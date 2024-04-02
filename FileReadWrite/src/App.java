import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class App {

    public static final String filename = "text.txt";

    public static void main(String[] args) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("Hello world");
        writer.write("\nHades white wolf");
        writer.write("\nThis is just a test");
        writer.close();

        System.out.println("The fila was written successfully\n");

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();

    }
}
