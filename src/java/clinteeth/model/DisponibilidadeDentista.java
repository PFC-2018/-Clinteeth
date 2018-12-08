package clinteeth.model;

import java.util.List;

/**
 *
 * @author Renan F Rodrigues
 */
public class DisponibilidadeDentista {
    private Dentista dentista;
    private List<String> diasDisponiveis;
    private List<String> horariosDisponiveis;

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public List<String> getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public void setDiasDisponiveis(List<String> diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
    
}
