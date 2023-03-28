import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.InputStream;
//import java.io.FileInputStream;
//import java.net.URL;

public class GeradoraDeFigurinha {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        //Leitura da Imagem
        
        //Arquivo Local 
        //InputStream inputStream = new FileInputStream(new File("entrada/filmes.jpg"));

        //Url da Web
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_4.jpg").openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Criar nova imagem
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copiar a imagem original para Nova imagem
        Graphics2D graphics =  (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        //Configurar a Fonte

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.BLUE);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);
        //Salvar a imagem em um novo arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
