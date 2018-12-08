
package clinteeth.model;

public class Motivo {
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Motivo() {
    }

    public Motivo(String descricao) {
        this.descricao = descricao;
    }

    
    public Motivo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    
}
