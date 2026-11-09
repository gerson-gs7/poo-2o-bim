package javaSwing;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class CalculadoraSwing extends JFrame {
    private final JTextField campoPrimeiroNumero = new JTextField();
    private final JTextField campoSegundoNumero = new JTextField();
    private final JLabel resultado = new JLabel("Resultado: ");

    public CalculadoraSwing() {
        super("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(5, 2, 8, 8));
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(new JLabel("Primeiro número:"));
        painel.add(campoPrimeiroNumero);
        painel.add(new JLabel("Segundo número:"));
        painel.add(campoSegundoNumero);

        adicionarBotao(painel, "Adição (+)", '+');
        adicionarBotao(painel, "Subtração (-)", '-');
        adicionarBotao(painel, "Multiplicação (*)", '*');
        adicionarBotao(painel, "Divisão (/)", '/');
        painel.add(resultado);

        add(painel);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarBotao(JPanel painel, String texto, char operacao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(event -> calcular(operacao));
        painel.add(botao);
    }

    private void calcular(char operacao) {
        final double primeiroNumero;
        final double segundoNumero;
        try {
            primeiroNumero = Double.parseDouble(campoPrimeiroNumero.getText().trim());
            segundoNumero = Double.parseDouble(campoSegundoNumero.getText().trim());
            if (Double.isNaN(primeiroNumero) || Double.isInfinite(primeiroNumero)
                    || Double.isNaN(segundoNumero) || Double.isInfinite(segundoNumero)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite dois números válidos.",
                    "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (operacao == '/' && segundoNumero == 0) {
            JOptionPane.showMessageDialog(this, "Não é possível dividir por zero.",
                    "Operação inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double valor;
        switch (operacao) {
            case '+':
                valor = primeiroNumero + segundoNumero;
                break;
            case '-':
                valor = primeiroNumero - segundoNumero;
                break;
            case '*':
                valor = primeiroNumero * segundoNumero;
                break;
            case '/':
                valor = primeiroNumero / segundoNumero;
                break;
            default:
                throw new IllegalArgumentException("Operação desconhecida: " + operacao);
        }
        resultado.setText("Resultado: " + valor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSwing().setVisible(true));
    }
}
