package tiposDeDados;

import tiposEnumeradosEnum.CategoriaProduto;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco;
    private int quantidadeEmEstoque;
    private CategoriaProduto categoria;

    public Produto(int codigo, String descricao, double preco,
                   int quantidadeEmEstoque, CategoriaProduto categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
    }

    public double calcularValorTotalEmEstoque() {
        return preco * quantidadeEmEstoque;
    }

    public void exibirDados() {
        System.out.println("--- Dados do produto ---");
        System.out.println("Código: " + codigo);
        System.out.println("Descrição: " + descricao);
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.println("Quantidade em estoque: " + quantidadeEmEstoque);
        System.out.println("Categoria: " + categoria);
        System.out.printf("Valor total em estoque: R$ %.2f%n",
                calcularValorTotalEmEstoque());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}
