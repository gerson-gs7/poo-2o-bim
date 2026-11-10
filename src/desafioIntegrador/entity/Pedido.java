package desafioIntegrador.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import desafioIntegrador.enums.StatusPedidoEnum;
import desafioIntegrador.exceptions.CadastroInvalidoException;
import desafioIntegrador.exceptions.EstoqueInsuficienteException;

public class Pedido {
    private static final Object LOCK_ESTOQUE = new Object();

    private final int codigo;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private volatile StatusPedidoEnum status;
    private volatile String mensagemErro;

    public Pedido(int codigo, Cliente cliente, List<ItemPedido> itens)
            throws CadastroInvalidoException {
        if (cliente == null) {
            throw new CadastroInvalidoException("Selecione um cliente.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new CadastroInvalidoException("Lance pelo menos um item antes de fechar o pedido.");
        }
        this.codigo = codigo;
        this.cliente = cliente;
        this.itens = Collections.unmodifiableList(new ArrayList<>(itens));
        this.status = StatusPedidoEnum.PENDENTE;
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public synchronized boolean iniciarProcessamento() {
        if (status != StatusPedidoEnum.PENDENTE) {
            return false;
        }
        status = StatusPedidoEnum.EM_PROCESSAMENTO;
        mensagemErro = null;
        return true;
    }

    public void concluirProcessamento() {
        status = StatusPedidoEnum.CONCLUIDO;
    }

    public void retirarItensDoEstoque() throws EstoqueInsuficienteException {
        Map<Produto, Long> quantidadesPorProduto = new LinkedHashMap<>();
        for (ItemPedido item : itens) {
            quantidadesPorProduto.merge(item.getProduto(), (long) item.getQuantidade(), Long::sum);
        }

        synchronized (LOCK_ESTOQUE) {
            for (Map.Entry<Produto, Long> entrada : quantidadesPorProduto.entrySet()) {
                if (entrada.getValue() > Integer.MAX_VALUE) {
                    throw new EstoqueInsuficienteException(
                            "A quantidade solicitada de \"" + entrada.getKey().getDescricao()
                                    + "\" excede o estoque disponível.");
                }
                entrada.getKey().validarEstoque(entrada.getValue().intValue());
            }
            for (Map.Entry<Produto, Long> entrada : quantidadesPorProduto.entrySet()) {
                entrada.getKey().retirarDoEstoque(entrada.getValue().intValue());
            }
        }
    }

    public void falharProcessamento(String mensagem) {
        mensagemErro = mensagem;
        status = StatusPedidoEnum.FALHOU;
    }

    public double getValorTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getValorTotal();
        }
        return total;
    }
}
