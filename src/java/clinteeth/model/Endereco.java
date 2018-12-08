
package clinteeth.model;

public class Endereco {
    
    // ATRIBUTOS
    private int EnderecoID;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private EstadosEnum uf;
    private String complemento;
    private String cep;
    
    // GETTERS AND SETTERS DOS ATRIBUTOS

    public int getEnderecoID() {
        return EnderecoID;
    }

    public void setEnderecoID(int pessoaID) {
        this.EnderecoID = pessoaID;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EstadosEnum getUf() {
        return uf;
    }

    public void setUf(EstadosEnum uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    // CONSTRUTOR PADRÃO
    
    public Endereco(){
        
    }
    
    // SOBREGARGA DE CONSTRUTOR SEM ENDERECOID

    public Endereco(String logradouro, int numero, String bairro, String cidade, EstadosEnum uf, String complemento, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(int EnderecoID, String logradouro, int numero, String bairro, String cidade, EstadosEnum uf, String complemento, String cep) {
        this.EnderecoID = EnderecoID;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.cep = cep;
    }
    
    
    
}
