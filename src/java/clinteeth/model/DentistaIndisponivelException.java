package clinteeth.model;
/**
 *
 * @author Renan F Rodrigues
 */
public class DentistaIndisponivelException extends RuntimeException{
    
    private String msgErro;

    public DentistaIndisponivelException(String tipoErro) {
        String msg = null;
        if (tipoErro.equals("DIA")) {
            msg = "O dentista não atende no dia de semana especificado. Verifique a disponibilidade e selecione de acordo. ";
        } else if (tipoErro.equals("HORARIO")) {
            msg = "O dentista não atende no horário selecionado. Verifique a disponibilidade e selecione de acordo. ";
        } else {
            msg = "Você passou um tipo de erro incorreto ao construtor dessa exceção.";
        }
        msgErro = msg;
        System.out.println(msg.toUpperCase());
    }

    public String getMsgErro() {
        return msgErro;
    }
    
}
