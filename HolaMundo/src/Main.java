import java.util.List;
import java.util.ArrayList;

public class Main {

    public static int suma(int a, int b){
        return a + b;
    }
    public static float divide(float a, float b){
        return a / b;
    }
    public  static  void forexample(List<Integer> L){
        System.out.println("\nRecorriendo la lista con un bucle for:");
        for (int i = 0; i < L.size(); i++) {
            System.out.println(L.get(i));
        }
    }
    public static void whileExample(List<Integer> L){

        System.out.println("\nRecorriendo la lista desde un while");
        int i = 0;
        while (i < L.size()) {
            System.out.println(L.get(i) * 3.14f);
            i++;
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello world!");

        System.out.println(suma(2, 3));

        System.out.println("El valor dividido es " + divide(54.324f, 2.2432f));

        List<Integer> numeros = new ArrayList<>();

        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);

        forexample(numeros);

        whileExample(numeros);
    }

}
