package BiblioControl;

// com.google.gson es una librería de código abierto de Google para Java que permite trabajar con JSON
import com.google.gson.*;
// java.io contiene las clases para realizar operaciones de entrada y salida de datos
import java.io.*;
// java.net contiene las clases para realizar operaciones de red
import java.net.*;
import java.util.ArrayList;

/**
 * Clase BusquedaInternet
 * Contiene los metodos para buscar informacion en internet
 */
public class BusquedaInternet {

    /**
     * Método buscarPorTitulo
     * @param titulo Titulo del libro
     * @return devuelve un ArrayList de String con los títulos e ISBN de los libros que coinciden
     */
    public static ArrayList<Libro> buscarPorTitulo(String titulo) {
        ArrayList<Libro> resultados = new ArrayList<>();
        try {

            // Se crea una URL con la dirección de la API de Google Books codificando el título para que no haya problemas con los espacios
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=intitle:" + URLEncoder.encode(titulo, "UTF-8"));

            // Se abre la conexión, se obtiene el JSON, se convierte a un objeto de la clase JsonObject y se obtiene el array de items
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray items = jsonObject.getAsJsonArray("items");

            // Si hay items se recorre el array y se obtienen los datos de cada libro
            if (items != null) {
                for (JsonElement item : items) {
                    JsonObject volumeInfo = item.getAsJsonObject().getAsJsonObject("volumeInfo");
                    String bookTitle = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "Titulo Desconocido";
                    String isbn = volumeInfo.has("industryIdentifiers") ? volumeInfo.get("industryIdentifiers").getAsJsonArray().get(0).getAsJsonObject().get("identifier").getAsString() : "ISBN Desconocido";
                    String autor = volumeInfo.has("authors") ? volumeInfo.get("authors").getAsJsonArray().get(0).getAsString() : "Autor Desconocido";
                    resultados.add(new Libro(isbn, bookTitle, autor));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al realizar la solicitud a la API de Google Books.");
        }
        return resultados;
    }

}

