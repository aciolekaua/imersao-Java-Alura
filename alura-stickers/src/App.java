import java.util.List;
import java.io.InputStream;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!")

        //API IMdb 
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        //API Nasa APOD
        String url = "https://api.nasa.gov/planetary/apod?api_key=w9uHh3hcFJ4B5NDQmCrpUPTmJdDwZkzv0fxFvLI5&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinha();
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            //API IMdb 
            //String urlImage = conteudo.get("image").replaceAll("(@+)(.*).jpg", "$1.jpg");

            //API Nasa APOD
            

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);
            
            System.out.println(conteudo.getTitulo());
            //System.out.println(urlImage);
            //System.out.println(conteudo.get("imDbRating"));

        }
    }
}
//