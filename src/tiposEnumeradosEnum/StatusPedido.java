package tiposEnumeradosEnum;

public enum StatusPedido {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    EM_PREPARACAO("Em Preparação"),
    ENVIADO("Enviado"),
    ENTREGUE("Entregue");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
