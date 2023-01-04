import java.util.ArrayList;

public class AcessoPrincipal {

    private ArrayList<Usuario>usuarios = new ArrayList<>();


    public boolean cadastrarUsuario(String login,String senha){

        return true;
    }
    public Usuario loginUsuario(String login,String Senha){
        return null;
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
