package desafioIntegrador.entity;

import desafioIntegrador.exceptions.CadastroInvalidoException;
import desafioIntegrador.exceptions.EstoqueInsuficienteException;

public class Produto {
    private final int codigo;
    private final String descricao;
    private final double preco;
    private int quantidadeEmEstoque;

    public Produto(int codigo, String descricao, double preco, int quantidadeEmEstoque)
            throws CadastroInvalidoException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new CadastroInvalidoException("A descrição do produto é obrigatória.");
        }
        if (!Double.isFinite(preco) || preco <= 0) {
            throw new CadastroInvalidoException("O preço deve ser maior que zero.");
        }
        if (quantidadeEmEstoque < 0) {
            throw new CadastroInvalidoException("A quantidade em estoque não pode ser negativa.");
        }
        this.codigo = codigo;
        this.descricao = descricao.trim();
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public synchronized int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public synchronized void validarEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > quantidadeEmEstoque) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente de \"" + descricao + "\". Disponível: "
                            + quantidadeEmEstoque + ".");
        }
    }

    public synchronized void retirarDoEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        validarEstoque(quantidade);
        quantidadeEmEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao + " (estoque: " + getQuantidadeEmEstoque() + ")";
    }
}
