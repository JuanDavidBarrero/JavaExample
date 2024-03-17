public class Main {
    public static void main(String[] args) {

        Persona Juan = new Persona("Juanda", 27);
        Estudiante Hades = new Estudiante("Hades", 10, "Matematicas" );

        System.out.println("\n=======================\n");

        Juan.imprimirInformacion();

        System.out.println("\n=======================\n");

        Hades.imprimirInformacionEstudiante();

    }
}