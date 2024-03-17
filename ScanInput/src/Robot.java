public class Robot {
    private String nombre;
    private String tipo;

    public Robot(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public void imprimirInformacion() {
        System.out.println("Nombre del robot: " + nombre);
        System.out.println("Tipo de robot: " + tipo);
    }
}
