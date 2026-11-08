package tratamentoDeExcecoes;

import java.util.Scanner;

public class SistemaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite a primeira nota (0 a 10): ");
            double primeiraNota = validarNota(Double.parseDouble(scanner.nextLine().trim()));

            System.out.print("Digite a segunda nota (0 a 10): ");
            double segundaNota = validarNota(Double.parseDouble(scanner.nextLine().trim()));

            double media = (primeiraNota + segundaNota) / 2;
            System.out.printf("Média: %.2f%n", media);
            System.out.println(media >= 6 ? "Aluno aprovado." : "Aluno reprovado.");
        } catch (NumberFormatException e) {
            System.out.println("Nota inválida. Digite um valor numérico.");
        } catch (NotaForaDoIntervaloException e) {
            System.out.println("Nota inválida: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double validarNota(double nota) throws NotaForaDoIntervaloException {
        if (nota < 0 || nota > 10) {
            throw new NotaForaDoIntervaloException("a nota deve estar entre 0 e 10.");
        }
        return nota;
    }
}
