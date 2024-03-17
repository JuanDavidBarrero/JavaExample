import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, introduce el nombre del robot:");
        String nombre = scanner.nextLine();

        System.out.println("Por favor, introduce el tipo de robot:");
        String tipo = scanner.nextLine();

        Robot robot = new Robot(nombre, tipo);

        robot.imprimirInformacion();

        scanner.close();
    }
}
