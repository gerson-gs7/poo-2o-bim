package javaSwing;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class CadastroAlunosSwing extends JFrame {
    private final JTextField campoNome = new JTextField();
    private final JTextField campoIdade = new JTextField();
    private final JComboBox<String> campoCurso = new JComboBox<>(
            new String[] {"Java", "Análise e Desenvolvimento de Sistemas", "Ciência da Computação"});

    public CadastroAlunosSwing() {
        super("Cadastro de alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(4, 2, 8, 8));
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Idade:"));
        painel.add(campoIdade);
        painel.add(new JLabel("Curso:"));
        painel.add(campoCurso);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(event -> salvarAluno());
        painel.add(new JLabel());
        painel.add(botaoSalvar);

        add(painel);
        pack();
        setLocationRelativeTo(null);
    }

    private void salvarAluno() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do aluno.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        final int idade;
        try {
            idade = Integer.parseInt(campoIdade.getText().trim());
            if (idade < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe uma idade válida, igual ou maior que zero.",
                    "Idade inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String curso = (String) campoCurso.getSelectedItem();
        JOptionPane.showMessageDialog(this,
                "Aluno cadastrado:\nNome: " + nome + "\nIdade: " + idade + "\nCurso: " + curso,
                "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroAlunosSwing().setVisible(true));
    }
}
