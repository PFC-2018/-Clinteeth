package clinteeth.model;

public class Login {

    private int loginID;
    private String email;
    private String senha;
    private PessoasEnum tipo;
    private String st;
    private Pessoa pessoa;    

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PessoasEnum getTipo() {
        return tipo;
    }

    public void setTipo(PessoasEnum tipo) {
        this.tipo = tipo;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Login() {
    }

    public Login(int loginID, String email, String senha, PessoasEnum tipo) {
        this.loginID = loginID;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    public Login(int loginID, String email, String senha, PessoasEnum tipo, String st, Pessoa pessoa) {
        this.loginID = loginID;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.st = st;
        this.pessoa = pessoa;
    }
}
