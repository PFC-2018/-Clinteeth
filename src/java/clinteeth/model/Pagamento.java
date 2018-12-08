package clinteeth.model;

import java.util.Date;

public class Pagamento {

    private int pagamentoID;
    private Atendimento atendimento;
    private Dentista dentista;
    private Paciente paciente;
    private Date dtpagamento;
    private double valor;
    private double valorpago;
    private String situacao;

    public int getPagamentoID() {
        return pagamentoID;
    }

    public void setPagamentoID(int pagamentoID) {
        this.pagamentoID = pagamentoID;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDtpagamento() {
        return dtpagamento;
    }

    public void setDtpagamento(Date dtpagamento) {
        this.dtpagamento = dtpagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Pagamento() {
    }

    public Pagamento(int pagamentoID, Atendimento atendimento, Dentista dentista, Paciente paciente, Date dtpagamento, double valor, double valorpago, String situacao) {
        this.pagamentoID = pagamentoID;
        this.atendimento = atendimento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.valorpago = valorpago;
        this.situacao= situacao;
    }

    public Pagamento(Atendimento atendimento, Dentista dentista, Paciente paciente, Date dtpagamento, double valor, double valorpago, String situacao) {
        this.atendimento = atendimento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.valorpago = valorpago;
        this.situacao= situacao;
    }

    

    
    
    
}

