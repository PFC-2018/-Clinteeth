
package clinteeth.model;

import java.util.Date;


public class Paciente extends Pessoa{
    private int pacienteID;
    private Pessoa pessoa;
    private String convenio;
    private Agendamento consulta;

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Agendamento getConsulta() {
        return consulta;
    }

    public void setConsulta(Agendamento consulta) {
        this.consulta = consulta;
    }

    public Paciente(int pacienteID, String convenio) {
        this.pacienteID = pacienteID;
        this.convenio = convenio;
    }

    public Paciente() {
    }

    public Paciente(int pacienteID, Pessoa pessoa, String convenio) {
        this.pacienteID = pacienteID;
        this.pessoa = pessoa;
        this.convenio = convenio;
    }

    public Paciente(int pacienteID, Pessoa pessoa, String convenio, Agendamento consulta) {
        this.pacienteID = pacienteID;
        this.pessoa = pessoa;
        this.convenio = convenio;
        this.consulta = consulta;
    }

    public Paciente(int pacienteID) {
        this.pacienteID = pacienteID;
    }
    
    
}
