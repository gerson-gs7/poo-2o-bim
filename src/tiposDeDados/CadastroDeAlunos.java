package tiposDeDados;
import java.util.Scanner;

public class CadastroDeAlunos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Altura (use ponto para casas decimais, ex.: 1.75): ");
        double altura = Double.parseDouble(scanner.nextLine());

        System.out.print("Está matriculado? (true/false): ");
        boolean matriculado = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Nota final: ");
        double notaFinal = Double.parseDouble(scanner.nextLine());

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.println("\n--- Dados do aluno ---");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Altura: " + altura + " m");
        System.out.println("Situação da matrícula: "
                + (matriculado ? "Matriculado" : "Não matriculado"));
        System.out.println("Nota final: " + notaFinal);
        System.out.println("Curso: " + curso);
        System.out.println("Resultado: "
                + (notaFinal >= 6 ? "Aprovado" : "Reprovado"));

        scanner.close();
    }
}
