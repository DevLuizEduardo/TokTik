public class Publicacao implements Comparable<Publicacao>{
    private String vocabulario;
    private String referencia;


    public Publicacao() {
    }
    public Publicacao(String vocabulario, String referencia) {
        this.vocabulario = vocabulario;
        this.referencia = referencia;
    }


    public String getVocabulario() {
        return vocabulario;
    }

    public void setVocabulario(String vocabulario) {
        this.vocabulario = vocabulario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int compareTo(Publicacao o) {


        return this.getVocabulario().compareTo(o.getVocabulario());
    }

    @Override
    public String toString() {
        return vocabulario + referencia+"\n"  ;
    }

}
