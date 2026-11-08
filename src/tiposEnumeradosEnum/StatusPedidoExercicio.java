package tiposEnumeradosEnum;

public class StatusPedidoExercicio {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("Notebook");
        System.out.println("Status inicial: " + pedido.getStatus().getDescricao());

        pedido.setStatus(StatusPedido.APROVADO);
        System.out.println("Status atualizado: " + pedido.getStatus().getDescricao());
    }
}
