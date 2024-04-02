import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("\n=============== HTTP GET REQUEST =================\n");


        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users/3"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Imprimir el contenido de la respuesta
            System.out.println("Respuesta del servidor:");
            System.out.println(response.body());
        } else {
            System.out.println("La solicitud no fue exitosa. Código de estado: " + response.statusCode());
        }

        System.out.println("\n=============== HTTP POST REQUEST =================\n");


        String jsonBody = "{\"name\": \"Hades lobo\", \"job\": \"wolf\"}";

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            // Imprimir el contenido de la respuesta
            System.out.println("Respuesta del servidor:");
            System.out.println(response.body());
        } else {
            System.out.println("La solicitud no fue exitosa. Código de estado: " + response.statusCode());
        }

    }
}
