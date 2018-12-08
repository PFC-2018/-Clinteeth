
package clinteeth.model;


public enum PessoasEnum {

    
    ATENDENTE("atendente"),
    DENTISTA("dentista"),
    PACIENTE("paciente");

    
    private String tipo;

    private PessoasEnum(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    
}