import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //outra forma de chamar sem usar o var
        //var response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        //System.out.println(body);


        //Extrair os Dados que queremos (titulo do filme,poster,nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //System.out.println(listaDeFilmes.size());
        //System.out.println(listaDeFilmes.get(0));

        //Exibir e manipular os dados do jeito que acharmos melhor
        var geradora = new GeradoraDeFigurinha();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();

            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);
            
            System.out.println(titulo);
            System.out.println(urlImage);
            System.out.println(filme.get("imDbRating"));
            System.out.println();

        }
    }
}
