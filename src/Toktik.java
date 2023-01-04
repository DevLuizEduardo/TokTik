import java.sql.SQLOutput;
import java.util.Scanner;

public class Toktik {


    public static void main(String[] args) {
         Usuario usuarioLogado = new Usuario();
         Usuario usuarioAux;
        AcessoPrincipal acessoPrincipal = new AcessoPrincipal();
        Scanner ler = new Scanner(System.in);

        boolean systemOn = true;
        boolean acesso = false;

        do {
            System.out.println("-------Bem vindo ao TokTik------\n" + "A rede social mais acessada do Brasil");
            System.out.println("\nPara acessar sua conta se ja tiver cadastro digite (1)");
            System.out.println("Para Cadastrar uma conta digite (2)");
            System.out.println("Para Sair digite (0)");
            String op = ler.nextLine();


            switch (op) {

                case "1": {
                    System.out.println("\n-----Bem vindo a Tela de Login-----\n");
                    do{
                        System.out.println("Digite um Login");
                        String login = ler.nextLine();
                        System.out.println("Digite uma senha");
                        String senha = ler.nextLine();
                      usuarioLogado = acessoPrincipal.loginUsuario(login,senha);
                    }while (usuarioLogado == null);
                    acesso = true;
                    break;

                }
                case "2": {
                    boolean cadastro ;
                    System.out.println("\n-----Bem vindo a Tela de Cadastro-----\n");
                    do{
                    System.out.println("Digite um Login");
                    String login = ler.nextLine();
                    System.out.println("Digite uma senha");
                    String senha = ler.nextLine();
                   cadastro= acessoPrincipal.cadastrarUsuario(login,senha);

                    }while (cadastro == false);
                    System.out.println("Cadastro Realizado com sucesso!!!");

                    break;

                }
                case "0": {
                    systemOn = false;
                    break;
                }
            }

            while (acesso == true){

                System.out.println("------Bem vindo a Tela Principal do Toktik--------");
                System.out.println("Para Buscar uma Postagem digite (1)");
                System.out.println("Para Criar uma Publicaco digite (2)");
                System.out.println("Para Logout digite (0)");
                op = ler.nextLine();

                switch (op){

                    case "1":{

                    }
                    case "2":{
                        System.out.println("----Tela de Pulicacao----\n");
                        System.out.print("Digite o Titulo :");
                        String titulo = ler.nextLine();
                        System.out.print("\n Digite o Formato da Publicao :");
                        String formato = ler.nextLine();
                        System.out.println("\n Digite a Legenda da Publicao");
                        String legenda = ler.nextLine();
                        usuarioLogado.criarPublicacao(titulo,formato,legenda);


                    }
                    case "0":{
                        acesso = false;
                        break;
                    }
                }
            }





        }while (systemOn == true);

    }
}
