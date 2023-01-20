import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AcessoPrincipal {

    Scanner ler = new Scanner(System.in);

    public AcessoPrincipal() {
        carregarLogins();
    }

    private ArrayList<Usuario>usuarios = new ArrayList<>();



    public Usuario cadastrarUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Digite um Login");
        usuario.setLogin(ler.nextLine());
        System.out.println("Digite uma senha");
        usuario.setSenha(ler.nextLine());

 if(buscarLogin(usuario)){
     System.out.println("Login ja existe, tente novamente!!!");
     return null;
 }else {
     salvarLogin(usuario);
     System.out.println("Cadastro Realizado com sucesso!!!");
     usuario.carregarArquivos();
     return usuario;
 }
    }




    public Usuario loginUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Digite um Login");
         usuario.setLogin(ler.nextLine());
        System.out.println("Digite uma senha");
        usuario.setSenha(ler.nextLine());

        if(buscarLogin(usuario)){
            usuario.carregarArquivos();
            return usuario;
        }else {
            return null;
        }

    }


    private void salvarLogin(Usuario usuario){


        File diretorio = new File("Logins");
        if(!diretorio.exists()) {
            diretorio.mkdir();
        }
        File file = new File(diretorio,"Usuarios.txt");
        try(FileWriter arquivo = new FileWriter(file,true);
            BufferedWriter buf = new BufferedWriter(arquivo);
            PrintWriter salvar = new PrintWriter(buf)){
            salvar.println(usuario.toString());
            salvar.flush();



        }catch (IOException e){
            e.printStackTrace();

        }
    }

    private boolean buscarLogin(Usuario usuario){
        Collections.sort(this.usuarios);

        int pos = Collections.binarySearch(this.usuarios,usuario);

        if (pos>=0){
            return this.usuarios.get(pos).getSenha().equals(usuario.getSenha());
        }else {
            return false;
        }



    }

    public void carregarLogins(){

        File file = new File("Logins");
        File arquivo = new File(file,"Usuarios.txt");
        if(!arquivo.exists()){
            file.mkdir();
        }else{

            try(FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr)){
                String linha = br.readLine();
                while (true) {

                    if (linha != null) {

                        String []dado = linha.split(",");
                        this.usuarios.add(new Usuario(dado[0],dado[1]));
                    }else {
                        break;

                    }
                    linha = br.readLine();
                }


            }catch ( IOException e){
                e.printStackTrace();

            }
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
