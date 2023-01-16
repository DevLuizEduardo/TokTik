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
                    usuarioLogado =acessoPrincipal.loginUsuario();


                    if(usuarioLogado !=null){
                        acesso = true;

                    }else {
                        System.out.println("Usuario ou Senha Incorreto!!!");
                    }
                    break;

                }
                case "2": {
                    boolean cadastro ;
                    System.out.println("\n-----Bem vindo a Tela de Cadastro-----\n");

                  acessoPrincipal.cadastrarUsuario();

                    break;

                }
                case "0": {
                    systemOn = false;
                    break;
                }
            }

            while (acesso ){

                System.out.println("------Bem vindo a Tela Principal do Toktik--------");
                System.out.println("Para Buscar uma Postagem digite (1)");
                System.out.println("Para Criar uma Publicaco digite (2)");
                System.out.println("Para Logout digite (0)");
                op = ler.nextLine();

                switch (op){

                    case "1":{
                        break;

                    }
                    case "2":{
                        System.out.println("----Tela de Pulicacao----\n");
                        System.out.print("Digite sua publicacao\n\n");
                        String post = ler.nextLine();
                        System.out.println("Publicar Sim(1) | Nao (2)");
                        int resposta = ler.nextInt();

                        if (resposta == 1){
                            usuarioLogado.criarPublicacao(post);
                            System.out.println("Seu texto foi publicado com sucesso!!!");
                            break;
                        }else{
                            System.out.println("Seu texto não foi publicado!!!");
                            break;
                        }




                    }
                    case "0":{
                        acesso = false;
                        break;
                    }
                }
            }





        }while (systemOn );

    }
}
