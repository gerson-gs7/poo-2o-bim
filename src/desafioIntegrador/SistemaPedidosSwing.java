package desafioIntegrador;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import desafioIntegrador.entity.Cliente;
import desafioIntegrador.entity.ItemPedido;
import desafioIntegrador.entity.Pedido;
import desafioIntegrador.entity.Produto;
import desafioIntegrador.enums.StatusPedidoEnum;
import desafioIntegrador.exceptions.CadastroInvalidoException;
import desafioIntegrador.exceptions.EstoqueInsuficienteException;

public class SistemaPedidosSwing extends JFrame {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<ItemPedido> itensEmRascunho = new ArrayList<>();
    private Cliente clienteEmRascunho;
    private final AtomicInteger idCliente = new AtomicInteger(1);
    private final AtomicInteger idProduto = new AtomicInteger(1);
    private final AtomicInteger idPedido = new AtomicInteger(1);

    private final DefaultTableModel modeloClientes = modeloTabela(
            "Código", "Nome", "E-mail");
    private final DefaultTableModel modeloProdutos = modeloTabela(
            "Código", "Descrição", "Preço", "Estoque");
    private final DefaultTableModel modeloItensRascunho = modeloTabela(
            "Produto", "Quantidade", "Preço unitário", "Subtotal");
    private final DefaultTableModel modeloPedidos = modeloTabela(
            "Pedido", "Cliente", "Itens", "Total", "Status");
            
    private JTable tabelaPedidos;
    private JTable tabelaItensRascunho;

    private final JTextField nomeCliente = new JTextField();
    private final JTextField emailCliente = new JTextField();
    private final JTextField descricaoProduto = new JTextField();
    private final JTextField precoProduto = new JTextField();
    private final JTextField estoqueProduto = new JTextField();
    private final JComboBox<Cliente> clientePedido = new JComboBox<>();
    private final JComboBox<Produto> produtoPedido = new JComboBox<>();
    private final JTextField quantidadePedido = new JTextField();

    public SistemaPedidosSwing() {
        super("Sistema de gerenciamento de pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Clientes", criarAbaClientes());
        abas.addTab("Produtos", criarAbaProdutos());
        abas.addTab("Pedidos", criarAbaPedidos());
        add(abas);

        carregarDadosIniciais();
        atualizarClientes();
        atualizarProdutos();

        setSize(950, 650);
        setLocationRelativeTo(null);
    }

    private void carregarDadosIniciais() {
        try {
            clientes.add(new Cliente(1, "Ana Souza", "ana.souza@email.com"));
            clientes.add(new Cliente(2, "Bruno Lima", "bruno.lima@email.com"));

            produtos.add(new Produto(1, "Notebook", 3299.90, 8));
            produtos.add(new Produto(2, "Mouse", 59.90, 25));
            produtos.add(new Produto(3, "Teclado", 129.90, 14));
            produtos.add(new Produto(4, "Monitor", 899.00, 6));
            produtos.add(new Produto(5, "Headset", 189.50, 11));
            produtos.add(new Produto(6, "Webcam", 249.90, 4));
            produtos.add(new Produto(7, "Cabo USB", 24.90, 40));
            produtos.add(new Produto(8, "Pen drive", 39.90, 0));
            produtos.add(new Produto(9, "SSD 1 TB", 499.00, 3));
            produtos.add(new Produto(10, "Suporte para notebook", 89.90, 17));

            idCliente.set(3);
            idProduto.set(11);
        } catch (CadastroInvalidoException e) {
            throw new IllegalStateException("Não foi possível carregar os dados iniciais.", e);
        }
    }

    private JPanel criarAbaClientes() {
        JPanel aba = new JPanel(new BorderLayout(8, 8));
        aba.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));
        formulario.add(new JLabel("Nome:"));
        formulario.add(nomeCliente);
        formulario.add(new JLabel("E-mail:"));
        formulario.add(emailCliente);

        JButton cadastrar = new JButton("Cadastrar cliente");
        cadastrar.addActionListener(event -> cadastrarCliente());
        formulario.add(new JLabel());
        formulario.add(cadastrar);

        aba.add(formulario, BorderLayout.NORTH);
        aba.add(new JScrollPane(new JTable(modeloClientes)), BorderLayout.CENTER);
        return aba;
    }

    private JPanel criarAbaProdutos() {
        JPanel aba = new JPanel(new BorderLayout(8, 8));
        aba.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Descrição:"));
        formulario.add(descricaoProduto);
        formulario.add(new JLabel("Preço:"));
        formulario.add(precoProduto);
        formulario.add(new JLabel("Quantidade em estoque:"));
        formulario.add(estoqueProduto);

        JButton cadastrar = new JButton("Cadastrar produto");
        cadastrar.addActionListener(event -> cadastrarProduto());
        formulario.add(new JLabel());
        formulario.add(cadastrar);

        aba.add(formulario, BorderLayout.NORTH);
        aba.add(new JScrollPane(new JTable(modeloProdutos)), BorderLayout.CENTER);
        return aba;
    }

    private JPanel criarAbaPedidos() {
        JPanel aba = new JPanel(new BorderLayout(8, 8));
        aba.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Cliente:"));
        formulario.add(clientePedido);
        formulario.add(new JLabel("Produto:"));
        formulario.add(produtoPedido);

        JPanel quantidadeComAcao = new JPanel(new BorderLayout(8, 0));
        quantidadeComAcao.add(quantidadePedido, BorderLayout.CENTER);

        JButton lancarItem = new JButton("Lançar item");
        lancarItem.addActionListener(event -> lancarItem());
        quantidadeComAcao.add(lancarItem, BorderLayout.EAST);

        formulario.add(new JLabel("Quantidade:"));
        formulario.add(quantidadeComAcao);

        JButton fecharPedido = new JButton("Fechar pedido");
        fecharPedido.addActionListener(event -> fecharPedido());
        formulario.add(new JLabel());
        formulario.add(fecharPedido);

        JPanel painelRascunho = new JPanel(new BorderLayout(4, 4));
        painelRascunho.add(new JLabel("Itens do pedido em montagem:"), BorderLayout.NORTH);
        tabelaItensRascunho = new JTable(modeloItensRascunho);
        painelRascunho.add(new JScrollPane(tabelaItensRascunho), BorderLayout.CENTER);

        JPanel painelPedidos = new JPanel(new BorderLayout(4, 4));
        painelPedidos.add(new JLabel(
                "Pedidos fechados (use Ctrl ou Shift para selecionar vários):"), BorderLayout.NORTH);
        tabelaPedidos = new JTable(modeloPedidos);
        tabelaPedidos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        painelPedidos.add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);

        JButton processarSelecionados = new JButton("Processar pedidos selecionados");
        processarSelecionados.addActionListener(event -> processarPedidosSelecionados());
        painelPedidos.add(processarSelecionados, BorderLayout.SOUTH);

        JSplitPane tabelas = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, painelRascunho, painelPedidos);
        tabelas.setResizeWeight(0.35);

        aba.add(formulario, BorderLayout.NORTH);
        aba.add(tabelas, BorderLayout.CENTER);
        return aba;
    }

    private void cadastrarCliente() {
        try {
            Cliente cliente = new Cliente(idCliente.get(),
                    nomeCliente.getText(), emailCliente.getText());
            idCliente.incrementAndGet();
            clientes.add(cliente);
            nomeCliente.setText("");
            emailCliente.setText("");
            atualizarClientes();
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        } catch (CadastroInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void cadastrarProduto() {
        try {
            double preco = Double.parseDouble(precoProduto.getText().trim().replace(',', '.'));
            int estoque = Integer.parseInt(estoqueProduto.getText().trim());
            Produto produto = new Produto(idProduto.get(),
                    descricaoProduto.getText(), preco, estoque);
            idProduto.incrementAndGet();
            produtos.add(produto);
            descricaoProduto.setText("");
            precoProduto.setText("");
            estoqueProduto.setText("");
            atualizarProdutos();
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            mostrarErro("Informe um preço e uma quantidade válidos.");
        } catch (CadastroInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void lancarItem() {
        try {
            Cliente cliente = (Cliente) clientePedido.getSelectedItem();
            Produto produto = (Produto) produtoPedido.getSelectedItem();
            int quantidade = Integer.parseInt(quantidadePedido.getText().trim());

            if (clienteEmRascunho != null && !clienteEmRascunho.equals(cliente)) {
                mostrarErro("Todos os itens do pedido devem pertencer ao mesmo cliente.");
                return;
            }

            ItemPedido item = new ItemPedido(produto, quantidade);
            if (clienteEmRascunho == null) {
                clienteEmRascunho = cliente;
                clientePedido.setEnabled(false);
            }
            itensEmRascunho.add(item);
            quantidadePedido.setText("");
            atualizarItensRascunho();
        } catch (NumberFormatException e) {
            mostrarErro("Informe uma quantidade inteira válida.");
        } catch (CadastroInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void fecharPedido() {
        try {
            Pedido pedido = new Pedido(idPedido.get(), clienteEmRascunho, itensEmRascunho);
            idPedido.incrementAndGet();
            pedidos.add(pedido);
            itensEmRascunho.clear();
            clienteEmRascunho = null;
            clientePedido.setEnabled(true);
            quantidadePedido.setText("");
            atualizarItensRascunho();
            atualizarPedidos();
            JOptionPane.showMessageDialog(this, "Pedido fechado com status Pendente.",
                    "Pedido", JOptionPane.INFORMATION_MESSAGE);
        } catch (CadastroInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void processarPedidosSelecionados() {
        int[] linhasSelecionadas = tabelaPedidos.getSelectedRows();
        if (linhasSelecionadas.length == 0) {
            mostrarErro("Selecione um ou mais pedidos na tabela.");
            return;
        }
        for (int linha : linhasSelecionadas) {
            int indiceModelo = tabelaPedidos.convertRowIndexToModel(linha);
            if (indiceModelo < pedidos.size()) {
                iniciarProcessamento(pedidos.get(indiceModelo));
            }
        }
    }

    private void iniciarProcessamento(Pedido pedido) {
        if (!pedido.iniciarProcessamento()) {
            mostrarErro("Esse pedido já foi processado ou está em processamento.");
            return;
        }
        atualizarPedidos();

        Thread processamento = new Thread(() -> {
            try {
                pedido.retirarItensDoEstoque();
                Thread.sleep(2000);
                pedido.concluirProcessamento();
            } catch (EstoqueInsuficienteException e) {
                pedido.falharProcessamento(e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                pedido.falharProcessamento("O processamento foi interrompido.");
            } finally {
                SwingUtilities.invokeLater(() -> {
                    atualizarProdutos();
                    atualizarPedidos();
                    if (pedido.getStatus() == StatusPedidoEnum.FALHOU) {
                        mostrarErro("Pedido " + pedido.getCodigo() + " não foi concluído: "
                                + pedido.getMensagemErro());
                    }
                });
            }
        }, "Processamento-Pedido-" + pedido.getCodigo());
        processamento.start();
    }

    private void atualizarClientes() {
        modeloClientes.setRowCount(0);
        clientePedido.removeAllItems();
        for (Cliente cliente : clientes) {
            modeloClientes.addRow(new Object[] {
                    cliente.getCodigo(), cliente.getNome(), cliente.getEmail()
            });
            clientePedido.addItem(cliente);
        }
    }

    private void atualizarProdutos() {
        modeloProdutos.setRowCount(0);
        produtoPedido.removeAllItems();
        for (Produto produto : produtos) {
            modeloProdutos.addRow(new Object[] {
                    produto.getCodigo(),
                    produto.getDescricao(),
                    String.format("R$ %.2f", produto.getPreco()),
                    produto.getQuantidadeEmEstoque()
            });
            produtoPedido.addItem(produto);
        }
    }

    private void atualizarPedidos() {
        modeloPedidos.setRowCount(0);
        for (Pedido pedido : pedidos) {
            StringBuilder itens = new StringBuilder();
            for (ItemPedido item : pedido.getItens()) {
                if (itens.length() > 0) {
                    itens.append(", ");
                }
                itens.append(item.getProduto().getDescricao())
                        .append(" (").append(item.getQuantidade()).append(")");
            }
            modeloPedidos.addRow(new Object[] {
                    pedido.getCodigo(),
                    pedido.getCliente().getNome(),
                    itens.toString(),
                    String.format("R$ %.2f", pedido.getValorTotal()),
                    pedido.getStatus()
            });
        }
    }

    private void atualizarItensRascunho() {
        modeloItensRascunho.setRowCount(0);
        for (ItemPedido item : itensEmRascunho) {
            modeloItensRascunho.addRow(new Object[] {
                    item.getProduto().getDescricao(),
                    item.getQuantidade(),
                    String.format("R$ %.2f", item.getProduto().getPreco()),
                    String.format("R$ %.2f", item.getValorTotal())
            });
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Não foi possível concluir",
                JOptionPane.WARNING_MESSAGE);
    }

    private static DefaultTableModel modeloTabela(String... colunas) {
        return new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaPedidosSwing().setVisible(true));
    }
}
