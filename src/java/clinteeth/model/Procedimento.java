
package clinteeth.model;

public class Procedimento {
    
    private int procedimentoID;
    private String descricao;
    private double preco;

    

    public int getprocedimentoID() {
        return procedimentoID;
    }

    public void setprocedimentoID(int procedimentoID) {
        this.procedimentoID = procedimentoID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Procedimento() {
    }

    public Procedimento(int procedimentoID, String descricao, double preco) {
        this.procedimentoID = procedimentoID;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Procedimento(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }
    
    
    
}
