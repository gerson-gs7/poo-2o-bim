package tratamentoDeExcecoes;

import java.util.Scanner;

public class CadastroValidado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nome: ");
            String nome = validarNome(scanner.nextLine());

            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine().trim());
            validarIdade(idade);

            System.out.print("CPF (11 dígitos, com ou sem pontuação): ");
            String cpf = validarCpf(scanner.nextLine());

            System.out.println("\nCadastro realizado com sucesso!");
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("CPF: " + cpf);
        } catch (NomeInvalidoException | IdadeInvalidaException | CPFInvalidoException e) {
            System.out.println("Não foi possível realizar o cadastro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Idade inválida. Digite a idade usando números inteiros.");
        } finally {
            scanner.close();
        }
    }

    private static String validarNome(String nome) throws NomeInvalidoException {
        String nomeNormalizado = nome.trim();
        if (nomeNormalizado.isEmpty()) {
            throw new NomeInvalidoException("O nome não pode ficar vazio.");
        }
        return nomeNormalizado;
    }

    private static void validarIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0) {
            throw new IdadeInvalidaException("A idade não pode ser inferior a zero.");
        }
    }

    private static String validarCpf(String cpf) throws CPFInvalidoException {
        String cpfValido = cpf.replaceAll("[.\\-\\s]", "");
        if (!cpfValido.matches("\\d{11}")) {
            throw new CPFInvalidoException("O CPF deve conter exatamente 11 dígitos.");
        }
        return cpfValido;
    }
}
