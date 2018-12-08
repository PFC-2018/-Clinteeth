
package clinteeth.model;

import java.util.Date;
import java.sql.Time;

public class Agendamento {
    
    private int agendamentoID;
    private Dentista dentista;
    private Paciente paciente;
    private Date dtagendamento;
    private Time hragendamento;
    private String titulo;
    private String observacao;
    private String situacao;

    public int getAgendamentoID() {
        return agendamentoID;
    }

    public void setAgendamentoID(int agendamentoID) {
        this.agendamentoID = agendamentoID;
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

    public Date getDtagendamento() {
        return dtagendamento;
    }

    public void setDtagendamento(Date dtagendamento) {
        this.dtagendamento = dtagendamento;
    }

    public Time getHragendamento() {
        return hragendamento;
    }

    public void setHragendamento(Time hragendamento) {
        this.hragendamento = hragendamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Agendamento() {
    }

    public Agendamento(int agendamentoID, Dentista dentista, Paciente paciente, Date dtagendamento, Time hragendamento, String titulo, String observacao, String situacao) {
        this.agendamentoID = agendamentoID;
        this.dentista = dentista;
        this.paciente = paciente;
        this.dtagendamento = dtagendamento;
        this.hragendamento = hragendamento;
        this.titulo = titulo;
        this.observacao = observacao;
        this.situacao = situacao;
    }



   

    public Agendamento(Date dtagendamento, Time hragendamento, Dentista dentista) {
        this.dtagendamento = dtagendamento;
        this.hragendamento = hragendamento;
        this.dentista = dentista;
    }
    
    
    
    
}
