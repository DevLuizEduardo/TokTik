import java.io.*;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private String login;
    private String senha;
    private Perfil perfil;


    public Usuario() {
    }



    public void criarPublicacao(String texto){
        File lista = new File("Postagens");
        lista.mkdir();
        File [] qtd = lista.listFiles();
        int i = qtd.length;
        File arquivo = new File("Postagens",i+".txt");
        try (FileWriter novo = new FileWriter(arquivo)){
            novo.write(texto);
            novo.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public void buscarPerfil(String nickName){



    }

    private void chaves(){

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
