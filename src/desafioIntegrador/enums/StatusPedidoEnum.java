package desafioIntegrador.enums;

public enum StatusPedidoEnum {
    PENDENTE("Pendente"),
    EM_PROCESSAMENTO("Em processamento"),
    CONCLUIDO("Concluído"),
    FALHOU("Falhou");

    private final String descricao;

    StatusPedidoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
