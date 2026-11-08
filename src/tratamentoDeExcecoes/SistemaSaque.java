package tratamentoDeExcecoes;

import java.util.Scanner;

public class SistemaSaque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaBancaria conta = new ContaBancaria(1000.00);

        try {
            System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
            System.out.print("Digite o valor do saque: ");
            double valor = Double.parseDouble(scanner.nextLine().trim());
            conta.sacar(valor);
            System.out.printf("Saque realizado. Saldo restante: R$ %.2f%n", conta.getSaldo());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um valor numérico.");
        } catch (SaldoInsuficienteException | IllegalArgumentException e) {
            System.out.println("Não foi possível realizar o saque: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
