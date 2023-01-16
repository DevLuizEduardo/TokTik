import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {
    private String login;
    private String senha;
    private Perfil perfil;


    public Usuario() {
    }

    private int qtdPost(){
        File lista = new File("Postagens");
        if(!lista.exists()){
            lista.mkdir();
        }
        File [] qtd = lista.listFiles();
        return qtd.length;

    }

    public void criarPublicacao(String texto){


        int i = qtdPost();
        File arquivo = new File("Postagens",i+".txt");
        try (FileWriter novo = new FileWriter(arquivo)){
            novo.write(texto);
            novo.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    private ArrayList<String> capturarVocabulario(String texto){

        ArrayList<String> vocabulario = new ArrayList<>( );


        Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
        Matcher m = p.matcher(texto);
        while(m.find()) {

            if (!vocabulario.contains(m.group()) && m.group().length() >2 ){
                vocabulario.add(m.group().toLowerCase(Locale.ROOT));
            }

        }
        Collections.sort(vocabulario);

        return vocabulario;

    }

    private ArrayList<String>readVocabularios(){

        ArrayList<String>vocabulario = new ArrayList<>();

        File file = new File("Vocabulario","chaves.txt");
            try (FileReader fr = new FileReader(file);
                 BufferedReader br = new BufferedReader(fr)) {
                String linha = br.readLine();
                while (true) {

                    if (linha != null) {
                        vocabulario.add(linha);
                    } else {
                        break;

                    }
                    linha = br.readLine();
                }


            } catch (IOException e) {
                e.printStackTrace();

            }


        return vocabulario;
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
