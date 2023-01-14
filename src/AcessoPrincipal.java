import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class AcessoPrincipal {

    Scanner ler = new Scanner(System.in);

    private ArrayList<Usuario>usuarios = new ArrayList<>();


    public void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Digite um Login");
        usuario.setLogin(ler.nextLine());
        System.out.println("Digite uma senha");
        usuario.setSenha(ler.nextLine());

 if(buscarLogin(usuario) !=null){
     System.out.println("Login ja existe, tente novamente!!!");
 }else {
     salvarLogin(usuario);
     System.out.println("Cadastro Realizado com sucesso!!!");
 }
    }




    public Usuario loginUsuario(){

        Usuario usuario = new Usuario();
        System.out.println("Digite um Login");
         usuario.setLogin(ler.nextLine());
        System.out.println("Digite uma senha");
        usuario.setSenha(ler.nextLine());


        Path path = Paths.get("Logins/"+usuario.getLogin()+".bin");


       if (Files.exists(path) ) {//verifica se o arquivo de login existe, se não,retorna nulo

           try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {

               Usuario inUsuario = (Usuario) ois.readObject();

               if (inUsuario.getSenha().equals(usuario.getSenha())) {// verifica se a senha
                   return inUsuario;
               } else {
                   return null;
               }

           } catch (IOException | ClassNotFoundException e) {

               e.printStackTrace();

           }


       }

       return null;
    }


    private void salvarLogin(Usuario usuario){


        File diretorio = new File("Logins");
        diretorio.mkdir();
        Path path = Paths.get("Logins/"+usuario.getLogin()+".bin");

        try (ObjectOutputStream oss = new ObjectOutputStream(Files.newOutputStream(path))){
            oss.writeObject(usuario);

        }catch (IOException e){
            e.printStackTrace();

        }
    }

    private Usuario buscarLogin(Usuario usuario){


        Path path = Paths.get("Logins/"+usuario.getLogin()+".bin");

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){

            Usuario inUsuario = (Usuario) ois.readObject();
            ois.close();

            return inUsuario;

        }catch (IOException  | ClassNotFoundException e ){

            return null;

        }

    }
    public void buscarPublicacao(String busca){

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
