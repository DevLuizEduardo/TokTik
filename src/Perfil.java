import java.util.ArrayList;
import java.util.Arrays;

public class Perfil {
    private int qtdSeguidores;
    private int qtdSeguindo;
    private int qtdPublicacao;
    private ArrayList<Publicacao>publicacaos = new ArrayList<>();
    private ArrayList<Usuario>Seguindo = new ArrayList<>();
    private ArrayList<Usuario>Seguidores = new ArrayList<>();

    public void verSeguidores(){

    }
    public void verSeguindo(){

    }
    public void verPublicacoes(){

    }
    public void seguirPerfil(){

    }

    public int getQtdSeguidores() {
        return qtdSeguidores;
    }

    public void setQtdSeguidores(int qtdSeguidores) {
        this.qtdSeguidores = qtdSeguidores;
    }

    public int getQtdSeguindo() {
        return qtdSeguindo;
    }

    public void setQtdSeguindo(int qtdSeguindo) {
        this.qtdSeguindo = qtdSeguindo;
    }

    public int getQtdPublicacao() {
        return qtdPublicacao;
    }

    public void setQtdPublicacao(int qtdPublicacao) {
        this.qtdPublicacao = qtdPublicacao;
    }

    public ArrayList<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public void setPublicacaos(ArrayList<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    public ArrayList<Usuario> getSeguindo() {
        return Seguindo;
    }

    public void setSeguindo(ArrayList<Usuario> seguindo) {
        Seguindo = seguindo;
    }

    public ArrayList<Usuario> getSeguidores() {
        return Seguidores;
    }

    public void setSeguidores(ArrayList<Usuario> seguidores) {
        Seguidores = seguidores;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "qtdSeguidores=" + qtdSeguidores +
                ", qtdSeguindo=" + qtdSeguindo +
                ", qtdPublicacao=" + qtdPublicacao +
                ", publicacaos=" + publicacaos +
                ", Seguindo=" + Seguindo +
                ", Seguidores=" + Seguidores +
                '}';
    }
}
