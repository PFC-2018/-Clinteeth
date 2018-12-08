package clinteeth.model;

import java.util.Date;

public class Pessoa {

    private int pessoaID;
    private String nome;
    private String rg;
    private String cpf;
    private Date dataNascimento;
    private String telefone;
    private String celular;
    private String st;
    private Login login;
    private Endereco endereco;

    public int getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(int pessoaID) {
        this.pessoaID = pessoaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa() {

    }

    public Pessoa(String nome, String rg, String cpf, Date dataNascimento, String telefone, String celular, Login login, String st, Endereco endereco) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.celular = celular;
        this.login = login;
        this.st = st;
        this.endereco = endereco;
    }

    public Pessoa(int pessoaID, String nome, String rg, String cpf, Date dataNascimento, String telefone, String celular, Login login, Endereco endereco) {
        this.pessoaID = pessoaID;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.celular = celular;
        this.login = login;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
