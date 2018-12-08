package clinteeth.model;

import java.util.ArrayList;


public class Atendimento {

    private int atendimentoID;
    private Agendamento agendamento;
    private Dentista dentista;
    private Paciente paciente;
    private ArrayList<Procedimento> procedimentos;
    private Double total;
    private Double desconto;
    private String observacoes;
    private String situacao;
    private String stPagamento;    
//    private int idmotcanc;

    public int getAtendimentoID() {
        return atendimentoID;
    }

    public void setAtendimentoID(int atendimentoID) {
        this.atendimentoID = atendimentoID;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
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

    public ArrayList<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(ArrayList<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getStPagamento() {
        return stPagamento;
    }

    public void setStPagamento(String stPagamento) {
        this.stPagamento = stPagamento;
    }

    
    
    public Atendimento() {
    }

    public Atendimento(int atendimentoID, Agendamento agendamento, Dentista dentista, Paciente paciente, ArrayList<Procedimento> procedimentos, Double total, Double desconto, String observacoes) {
        this.atendimentoID = atendimentoID;
        this.agendamento = agendamento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.procedimentos = procedimentos;
        this.total = total;
        this.desconto = desconto;
        this.observacoes = observacoes;
    }

    public Atendimento(int atendimentoID, Agendamento agendamento, Dentista dentista, Paciente paciente, Double total, Double desconto, String observacoes) {
        this.atendimentoID = atendimentoID;
        this.agendamento = agendamento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.total = total;
        this.desconto = desconto;
        this.observacoes = observacoes;
    }

    public Atendimento(Agendamento agendamento, Dentista dentista, Paciente paciente, Double total, Double desconto, String observacoes) {
        this.agendamento = agendamento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.total = total;
        this.desconto = desconto;
        this.observacoes = observacoes;
    }

    public Atendimento(int atendimentoID, Agendamento agendamento, Dentista dentista, Paciente paciente, Double total, Double desconto, String observacoes, String situacao, String stPagamento) {
        this.atendimentoID = atendimentoID;
        this.agendamento = agendamento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.total = total;
        this.desconto = desconto;
        this.observacoes = observacoes;
        this.situacao = situacao;
        this.stPagamento = stPagamento;
    }

  

    public Atendimento(Agendamento agendamento, Dentista dentista, Paciente paciente, ArrayList<Procedimento> procedimentos, Double total, Double desconto, String observacoes, String situacao, String stPagamento) {
        this.agendamento = agendamento;
        this.dentista = dentista;
        this.paciente = paciente;
        this.procedimentos = procedimentos;
        this.total = total;
        this.desconto = desconto;
        this.observacoes = observacoes;
        this.situacao = situacao;
        this.stPagamento = stPagamento;
    }
    
    

    
    public double CalcularTotal() {
        total = 0.0;
        for( Procedimento listaProc : procedimentos){
          total += listaProc.getPreco();
        }
         total = (total-desconto);
         return total;
    }

 
   
    
}