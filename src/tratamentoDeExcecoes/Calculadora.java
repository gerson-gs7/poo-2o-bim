package tratamentoDeExcecoes;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Calculadora");
            System.out.println("1 - Adição");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.print("Escolha uma operação: ");
            int operacao = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Digite o primeiro número: ");
            double primeiroNumero = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Digite o segundo número: ");
            double segundoNumero = Double.parseDouble(scanner.nextLine().trim());

            double resultado;
            switch (operacao) {
                case 1:
                    resultado = primeiroNumero + segundoNumero;
                    break;
                case 2:
                    resultado = primeiroNumero - segundoNumero;
                    break;
                case 3:
                    resultado = primeiroNumero * segundoNumero;
                    break;
                case 4:
                    if (segundoNumero == 0) {
                        throw new ArithmeticException("Não é possível dividir por zero.");
                    }
                    resultado = primeiroNumero / segundoNumero;
                    break;
                default:
                    throw new IllegalArgumentException("A operação escolhida não existe.");
            }

            System.out.println("Resultado: " + resultado);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite números e uma opção de 1 a 4.");
        } catch (ArithmeticException e) {
            System.out.println("Não foi possível realizar o cálculo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Opção inválida. Escolha um número de 1 a 4.");
        } finally {
            scanner.close();
            System.out.println("Calculadora encerrada.");
        }
    }
}
