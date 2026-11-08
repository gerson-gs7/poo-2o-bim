package tratamentoDeExcecoes;

public class CPFInvalidoException extends Exception {
    public CPFInvalidoException(String mensagem) {
        super(mensagem);
    }
}
