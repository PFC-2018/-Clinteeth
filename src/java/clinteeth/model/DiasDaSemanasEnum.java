package clinteeth.model;

public enum DiasDaSemanasEnum {
    
    DOMINGO("DOMINGO"),
    SEGUNDA("SEGUNDA-FEIRA"),
    TERCA("TERÇA-FEIRA"),
    QUARTA("QUARTA-FEIRA"),
    QUINTA("QUINTA-FEIRA"),
    SEXTA("SEXTA-FEIRA"),
    SABADO("SÁBADO");
   
    public String diasSemanas;

    private DiasDaSemanasEnum(String diasSemanas) {
        this.diasSemanas = diasSemanas;
    }
    
    public String getDiasSemanas(){
        return diasSemanas;
    }

    @Override
    public String toString() {
        return diasSemanas;
    }
    
    
    
}
