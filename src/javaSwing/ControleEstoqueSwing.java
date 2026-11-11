package javaSwing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import tiposDeDados.Produto;
import tiposEnumeradosEnum.CategoriaProduto;

public class ControleEstoqueSwing extends JFrame {
    private final JTextField campoCodigo = new JTextField();
    private final JTextField campoDescricao = new JTextField();
    private final JTextField campoPreco = new JTextField();
    private final JTextField campoQuantidade = new JTextField();
    private final JComboBox<CategoriaProduto> campoCategoria =
            new JComboBox<>(CategoriaProduto.values());
    private final List<Produto> produtos = new ArrayList<>();
    private final DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[] {"Código", "Descrição", "Preço", "Quantidade", "Categoria"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ControleEstoqueSwing() {
        super("Controle de estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(6, 2, 6, 6));
        formulario.add(new JLabel("Código:"));
        formulario.add(campoCodigo);
        formulario.add(new JLabel("Descrição:"));
        formulario.add(campoDescricao);
        formulario.add(new JLabel("Preço:"));
        formulario.add(campoPreco);
        formulario.add(new JLabel("Quantidade:"));
        formulario.add(campoQuantidade);
        formulario.add(new JLabel("Categoria:"));
        formulario.add(campoCategoria);

        JButton botaoInserir = new JButton("Inserir produto");
        botaoInserir.addActionListener(event -> inserirProduto());
        formulario.add(botaoInserir);

        JButton botaoConsultar = new JButton("Consultar por código");
        botaoConsultar.addActionListener(event -> consultarProduto());
        formulario.add(botaoConsultar);

        JTable tabela = new JTable(modeloTabela);
        JButton botaoListar = new JButton("Listar produtos");
        botaoListar.addActionListener(event -> listarProdutos());
        JButton botaoTotal = new JButton("Valor total do estoque");
        botaoTotal.addActionListener(event -> mostrarValorTotal());

        JPanel acoes = new JPanel();
        acoes.add(botaoListar);
        acoes.add(botaoTotal);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(acoes, BorderLayout.SOUTH);
        setSize(700, 420);
        setLocationRelativeTo(null);
    }

    private void inserirProduto() {
        try {
            int codigo = Integer.parseInt(campoCodigo.getText().trim());
            String descricao = campoDescricao.getText().trim();
            double preco = Double.parseDouble(campoPreco.getText().trim().replace(',', '.'));
            int quantidade = Integer.parseInt(campoQuantidade.getText().trim());
            CategoriaProduto categoria = (CategoriaProduto) campoCategoria.getSelectedItem();

            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a descrição do produto.",
                        "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (preco < 0 || Double.isNaN(preco) || Double.isInfinite(preco) || quantidade < 0) {
                JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser iguais ou maiores que zero.",
                        "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (buscarProduto(codigo) != null) {
                JOptionPane.showMessageDialog(this, "Já existe um produto com esse código.",
                        "Código duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }

            produtos.add(new Produto(codigo, descricao, preco, quantidade, categoria));
            limparFormulario();
            listarProdutos();
            JOptionPane.showMessageDialog(this, "Produto inserido com sucesso.",
                    "Estoque", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Verifique o código, o preço e a quantidade informados.",
                    "Entrada inválida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void listarProdutos() {
        modeloTabela.setRowCount(0);
        for (Produto produto : produtos) {
            modeloTabela.addRow(new Object[] {
                    produto.getCodigo(),
                    produto.getDescricao(),
                    String.format("R$ %.2f", produto.getPreco()),
                    produto.getQuantidadeEmEstoque(),
                    produto.getCategoria()
            });
        }
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ainda não há produtos cadastrados.",
                    "Estoque vazio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void consultarProduto() {
        String entrada = JOptionPane.showInputDialog(this, "Informe o código do produto:");
        if (entrada == null) {
            return;
        }

        try {
            int codigo = Integer.parseInt(entrada.trim());
            Produto produto = buscarProduto(codigo);
            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.",
                        "Consulta", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Produto: " + produto.getDescricao()
                            + "\nPreço: " + String.format("R$ %.2f", produto.getPreco())
                            + "\nQuantidade em estoque: " + produto.getQuantidadeEmEstoque(),
                    "Dados do produto", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O código deve ser um número inteiro.",
                    "Código inválido", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarValorTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.calcularValorTotalEmEstoque();
        }
        JOptionPane.showMessageDialog(this,
                "Valor total do estoque: " + String.format("R$ %.2f", total),
                "Total do estoque", JOptionPane.INFORMATION_MESSAGE);
    }

    private Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    private void limparFormulario() {
        campoCodigo.setText("");
        campoDescricao.setText("");
        campoPreco.setText("");
        campoQuantidade.setText("");
        campoCategoria.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControleEstoqueSwing().setVisible(true));
    }
}
