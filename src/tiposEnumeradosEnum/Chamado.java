package tiposEnumeradosEnum;

public class Chamado {
    private String descricao;
    private Prioridade prioridade;

    public Chamado(String descricao, Prioridade prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public String obterMensagemPrioridade() {
        switch (prioridade) {
            case BAIXA:
                return "Pode ser atendido quando houver disponibilidade.";
            case MEDIA:
                return "Deve ser atendido em breve.";
            case ALTA:
                return "Deve ser priorizado no atendimento.";
            case URGENTE:
                return "Atendimento imediato necessário!";
            default:
                throw new IllegalStateException("Prioridade não suportada: " + prioridade);
        }
    }
}
