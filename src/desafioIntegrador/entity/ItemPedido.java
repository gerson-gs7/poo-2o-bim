package desafioIntegrador.entity;

import desafioIntegrador.exceptions.CadastroInvalidoException;

public class ItemPedido {
    private final Produto produto;
    private final int quantidade;

    public ItemPedido(Produto produto, int quantidade) throws CadastroInvalidoException {
        if (produto == null) {
            throw new CadastroInvalidoException("Selecione um produto.");
        }
        if (quantidade <= 0) {
            throw new CadastroInvalidoException("A quantidade do item deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return produto.getPreco() * quantidade;
    }
}
