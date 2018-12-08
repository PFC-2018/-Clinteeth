package clinteeth.model;


public class Disponibilidade {
    
    private int disponibilidadeID;
    private Dentista dentista;
    private DiasDaSemanasEnum dtdisponivel;
    private HorasEnum hora;
    private String st;

    public int getDisponibilidadeID() {
        return disponibilidadeID;
    }

    public void setDisponibilidadeID(int disponibilidadeID) {
        this.disponibilidadeID = disponibilidadeID;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public DiasDaSemanasEnum getDtdisponivel() {
        return dtdisponivel;
    }

    public void setDtdisponivel(DiasDaSemanasEnum dtdisponivel) {
        this.dtdisponivel = dtdisponivel;
    }

    public HorasEnum getHora() {
        return hora;
    }

    public void setHora(HorasEnum hora) {
        this.hora = hora;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }



   

    public Disponibilidade() {
    }

    public Disponibilidade(Dentista dentista, DiasDaSemanasEnum dtdisponivel, HorasEnum hora) {
        this.dentista = dentista;
        this.dtdisponivel = dtdisponivel;
        this.hora = hora;
    }

    public Disponibilidade(int disponibilidadeID, Dentista dentista, DiasDaSemanasEnum dtdisponivel, HorasEnum hora, String st) {
        this.disponibilidadeID = disponibilidadeID;
        this.dentista = dentista;
        this.dtdisponivel = dtdisponivel;
        this.hora = hora;
        this.st = st;
    }

    public Disponibilidade(DiasDaSemanasEnum dtdisponivel, HorasEnum hora) {
        this.dtdisponivel = dtdisponivel;
        this.hora = hora;
    }

    public Disponibilidade(int disponibilidadeID, Dentista dentista, DiasDaSemanasEnum dtdisponivel, HorasEnum hora) {
        this.disponibilidadeID = disponibilidadeID;
        this.dentista = dentista;
        this.dtdisponivel = dtdisponivel;
        this.hora = hora;
    }


    
    
     
    
}
