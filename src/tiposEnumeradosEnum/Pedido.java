package tiposEnumeradosEnum;

public class Pedido {
    private String descricao;
    private StatusPedido status;

    public Pedido(String descricao) {
        this.descricao = descricao;
        this.status = StatusPedido.PENDENTE;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
