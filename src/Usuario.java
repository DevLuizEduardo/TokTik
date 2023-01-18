import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Comparable<Usuario>{
    private String login;
    private String senha;
    private Perfil perfil;
    private ArrayList<Publicacao>vocabularioFinal = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    //Retorna a quantidade de arquivos txt existentes
    private int qtdPost(){
        File lista = new File("Postagens");
        if(!lista.exists()){
            lista.mkdir();
        }
        File [] qtd = lista.listFiles();
        return qtd.length;

    }



    //Salva publicação e manda para o processo de captura de vocabulario
    public void criarPublicacao(String texto){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String hora = (dtf.format(LocalDateTime.now()));

        int i = qtdPost();
        File arquivo = new File("Postagens",i+".txt");
        try (FileWriter novo = new FileWriter(arquivo)){
            novo.write(texto+"\n"+"["+hora+"]");
            novo.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        capturarVocabulario(texto);

    }


    //Captuar vocabulario da publicação
    private void capturarVocabulario(String texto){

        ArrayList<Publicacao> vocabulario = new ArrayList<>( );
        ArrayList<Publicacao> vocaUnico = new ArrayList<>( );

        String idPost = String.valueOf(qtdPost()-1);

        Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
        Matcher m = p.matcher(texto);
        while(m.find()) {

            if (!vocabulario.contains(m.group()) && m.group().length() >2 ){
                vocabulario.add(new Publicacao(m.group().toLowerCase(Locale.ROOT),idPost));
            }

        }
        Collections.sort(vocabulario);

        //Eleminar vocabularios repetidos
        boolean unico ;

        for (int i = 0; i<vocabulario.size();i++){
            unico = true;
            for (int j = i+1; j <vocabulario.size();j++){
                if (vocabulario.get(i).getVocabulario().equals(vocabulario.get(j).getVocabulario())){
                    unico = false;
                }


            }

            if (unico){
                vocaUnico.add(vocabulario.get(i));
            }
        }


        joinVocabularios(vocaUnico);
    }

    //Verificar se o vocabulario já existe no arquivo de indexação
    public  void joinVocabularios (ArrayList<Publicacao>novoVocabulario) {
        if (this.vocabularioFinal.isEmpty()){
            salvarVocabulario(novoVocabulario);
            carregarArquivos();


        }else {

            ArrayList<Publicacao> vocabularioDiferente = new ArrayList<>();

            int i = 0;
            int pos;

            while (i != novoVocabulario.size()) {


                pos = Collections.binarySearch(this.vocabularioFinal, novoVocabulario.get(i));
                if (pos >= 0) {
                    String referencia = this.vocabularioFinal.get(pos).getReferencia();
                    this.vocabularioFinal.get(pos).setReferencia(referencia + "," + novoVocabulario.get(i).getReferencia());
                } else {
                    vocabularioDiferente.add(novoVocabulario.get(i));

                }

                i++;
            }

        //Adiciona vocabularios que não existe no arquivo de indexação
            if (!vocabularioDiferente.isEmpty()) {
                i = 0;

                while (i != vocabularioDiferente.size()) {
                    this.vocabularioFinal.add(vocabularioDiferente.get(i));
                    i++;
                }
            }

            Collections.sort(this.vocabularioFinal);
            salvarVocabulario(this.vocabularioFinal);



        }



    }

    //Atualizar a indexação
    private void salvarVocabulario(ArrayList<Publicacao> vocabulario){
        File file = new File("Vocabulario","chaves.txt");

        try {
            FileWriter arquivo = new FileWriter(file,false);
            PrintWriter printWriter = new PrintWriter(arquivo);

            for (Publicacao post : vocabulario){
                printWriter.print(post);
            }
            printWriter.flush();
            printWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }




    public void buscarPerfil(String nickName){



    }


//Adicina a indexação já salva em uma Arraylist do tipo Publicação
    public void carregarArquivos(){

        File file = new File("Vocabulario");
        File arquivo = new File(file,"chaves.txt");
        if(!arquivo.exists()){
            file.mkdir();
        }else{

            try(FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr)){
                String linha = br.readLine();
                while (true) {

                    if (linha != null) {


                        this.vocabularioFinal.add(new Publicacao(linha.replaceAll("[0-9]","").replaceAll(",","")
                                ,linha.replaceAll("[a-zA-Zà-úÀ-Ú]","")));
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


    public void bucarPost(String texto){
        ArrayList<Publicacao> vocabulario = new ArrayList<>( );
        ArrayList<Publicacao> vocaUnico = new ArrayList<>( );



        Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
        Matcher m = p.matcher(texto);
        while(m.find()) {

            if (!vocabulario.contains(m.group()) && m.group().length() >2 ){
                vocabulario.add(new Publicacao(m.group().toLowerCase(Locale.ROOT),null));
            }

        }
        Collections.sort(vocabulario);

        //Eleminar vocabularios repetidos
        boolean unico ;

        for (int i = 0; i<vocabulario.size();i++){
            unico = true;
            for (int j = i+1; j <vocabulario.size();j++){
                if (vocabulario.get(i).getVocabulario().equals(vocabulario.get(j).getVocabulario())){
                    unico = false;
                }


            }

            if (unico){
                vocaUnico.add(vocabulario.get(i));
            }
        }

        int pos;
        ArrayList<String> ref = new ArrayList<>();

        for (int i = 0; i<vocaUnico.size();i++) {
           pos= Collections.binarySearch(this.vocabularioFinal, vocaUnico.get(i));
            if (pos>=0){
                ref.add(this.vocabularioFinal.get(pos).getReferencia());
            }

        }


        if (ref.isEmpty()){

            System.out.println("Postagem nao encontrada");
        }else {

            retornarBusca(ref);
        }




    }


    public void retornarBusca(ArrayList<String> referencia){



        String s = referencia.toString().replaceAll("\\[","").replaceAll("]","").replaceAll(",","");

        ArrayList<Character> id = new ArrayList<Character>();
        HashSet<Character> seen = new HashSet<>();

        for(int i = 0;i< s.length();i++){
            char c = s.charAt(i);
            if(!seen.contains(c)){
                id.add(c);
                seen.add(c);
            }

        }



        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(id.toString());
        while(m.find()) {

            File arquivo = new File("Postagens",m.group()+".txt");
            try(FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr)){
                String linha = br.readLine();
                while (true) {

                    if (linha != null) {
                        System.out.println(linha);

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
