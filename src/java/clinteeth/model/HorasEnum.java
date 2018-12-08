package clinteeth.model;

public enum HorasEnum {
    
    SETE("07:00"),
    SETEMEIA("07:30"),
    OITO("08:00"),
    OITOMEIA("08:30"),
    NOVE("09:00"),
    NOVEMEIA("09:30"),
    DEZ("10:00"),
    DEZMEIA("10:30"),
    ONZE("11:00"),
    ONZEMEIA("11:30"),
    MEIODIA("12:00"),
    MEIODIAMEIO("12:30"),
    UMA("13:00"),
    UMAMEIA("13:30"),
    DUAS("14:00"),
    DUASMEIA("14:30"),
    TRES("15:00"),
    TRESMEIA("15:30"),
    QUATRO("16:00"),
    QUATROMEIA("16:30"),
    CINCO("17:00"),
    CINCOMEIA("17:30"),
    SEIS("18:00"),
    SEISMEIA("18:30");

    
    private String horas;

    private HorasEnum(String horas) {
        this.horas = horas;
    }
    
    public String getHoras(){
        return horas;
    }

    @Override
    public String toString() {
        return horas;
    }
    
    
    
    

    
}
