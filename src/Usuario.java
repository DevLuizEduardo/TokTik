import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Comparable<Usuario>{
    private String login;
    private String senha;
    private Perfil perfil;

    private TreeMap<String, String> vocabulario = new TreeMap<>();

    //Assim que a classe Usuario é estanciada,seu construtor chama o metodo carregarArquivo()
    public Usuario() {
        carregarArquivos();
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }





    //Carrega o arquivo que esta no computador com os indices e salva na Treemap  privada,"vocabulario"
    // Caso nao exista o arquivo ele cria o caminho para o arquivo txt
    public void carregarArquivos () {

        File file = new File("Vocabulario");
        File arquivo = new File(file, "chaves.txt");
        if (!arquivo.exists()) {
            file.mkdir();
        } else {
            try (FileReader fr = new FileReader(arquivo);
                 BufferedReader br = new BufferedReader(fr)) {
                String linha;
                while ((linha = br.readLine()) != null) {

                    String[] partes = linha.split(":");
                    this.vocabulario.put(partes[0], partes[1]);
                }


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }



    //Metodo que salva no computador o texto passado pelo usuario
    //o arquivo é salvo com o nome (qtdPosts()) que será  a referencia de sua posição na pasta onde ficará armazenada
    public void salvarPostagem (String texto){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String hora = (dtf.format(LocalDateTime.now()));

        int i = qtdPosts();
        File arquivo = new File("Postagens",i+".txt");
        try (FileWriter novo = new FileWriter(arquivo)){
            novo.write(texto+"\n"+"["+hora+"]");
            novo.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
        capturarvocabulario(texto);
    }



    //Metodo recebe o texto e filtra somente o vocabulario e salva em um Treemap
    public void capturarvocabulario (String texto){
        TreeMap<String, String> indice = new TreeMap<>();


        String idPost = String.valueOf(qtdPosts() - 1);
        Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
        Matcher m = p.matcher(texto);
        // o comando putIfAsent() irá armazenar as chaves que não possui
        // se existir a chave na variavel Treemap, ele retorna o valor contido,se não, retorna null
        while (m.find()) {
            // só adiciona vocabulario que tenha mais de dois caracteres
            if (m.group().length() > 2) {
                indice.putIfAbsent(m.group().toLowerCase(Locale.ROOT), idPost);
            }

        }

        //O indice com os novos vocabularios serão armazenados, caso já exista,será armazenado somente o endereço

        for (Map.Entry<String, String> entry : indice.entrySet()) {

            if (this.vocabulario.putIfAbsent(entry.getKey(), entry.getValue()) != null) {
                String aux = this.vocabulario.get(entry.getKey());
                this.vocabulario.put(entry.getKey(), aux + "," + entry.getValue());

            }

        }

        salvarvocabulario(this.vocabulario);

    }



    //Salva o Treemap em um arquivo txt e usa o "append:false" para sobrescrever o arquivo
    public static void salvarvocabulario (TreeMap < String, String > indice){
        File file = new File("vocabulario", "chaves.txt");

        try {
            FileWriter arquivo = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(arquivo);

            for (Map.Entry<String, String> entry : indice.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.flush();
                bw.newLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }


    //O metodo captura o vocabulario do texto de busca passodo pelo usuário
    public void buscarEndereco (String texto){

        HashSet<String> indice = new HashSet<>();
        HashSet<String> endereco = new HashSet<>();


        String idPost = String.valueOf(qtdPosts() - 1);
        Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
        Matcher m = p.matcher(texto);



        while (m.find()) {

            if (m.group().length() > 2) {
                indice.add(m.group().toLowerCase(Locale.ROOT));
            }
        }

        //captura o endereço onde se encontra o vocabulario e adiciona na variavel "endereco"
        for (String e : indice) {

            String ref = this.vocabulario.get(e);
            if (ref != null) {
                String[] id = ref.split(",");
                endereco.addAll(Arrays.asList(id));
            }
        }

        //caso não tenha nenhum endereço referente ao vocabularip na variavel "endereco"
        // ele retorna que não existe nenhuma publicação
        if (endereco.isEmpty()) {
            System.out.println("Nao existe nenhuma publicacao!!! :(");

        } else {

            //Converte o meu hashset endereco em uma lista
            ArrayList<String> list = new ArrayList<>(endereco);

            Collections.sort(list,Collections.reverseOrder());

            for (String c : list) {
                File arquivo = new File("Postagens", c + ".txt");
                try (FileReader fr = new FileReader(arquivo);
                     BufferedReader br = new BufferedReader(fr)) {
                    String linha = br.readLine();
                    while (true) {

                        if (linha != null) {
                            System.out.println(linha);

                        } else {
                            break;


                        }
                        linha = br.readLine();
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }


    }



    //Busca e retorna quantidade de arquivos existentes na pasta de Postagens
    //Caso não exista uma pasta ela será criada
    public static int qtdPosts () {
        File lista = new File("Postagens");
        if (!lista.exists()) {
            lista.mkdir();
        }
        File[] qtd = lista.listFiles();

        return qtd.length;

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

    @Override
    public String toString() {
        return login +","+ senha;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.getLogin().compareTo(o.getLogin());
    }
}
