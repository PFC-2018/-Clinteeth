package clinteeth.model;

public class Dentista extends Pessoa{
    
    private int dentistaID;
    private Pessoa pessoa;
    private String cro;
    private Disponibilidade disponibilidade;

    public int getDentistaID() {
        return dentistaID;
    }

    public void setDentistaID(int dentistaID) {
        this.dentistaID = dentistaID;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Dentista() {
    }

    public Dentista(int dentistaID, Pessoa pessoa, String cro) {
        this.dentistaID = dentistaID;
        this.pessoa = pessoa;
        this.cro = cro;
    }

    public Dentista(int dentistaID, Pessoa pessoa, String cro, Disponibilidade disponibilidade) {
        this.dentistaID = dentistaID;
        this.pessoa = pessoa;
        this.cro = cro;
        this.disponibilidade = disponibilidade;
    }

    public Dentista(int dentistaID) {
        this.dentistaID = dentistaID;
    }       
}
