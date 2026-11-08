package tiposEnumeradosEnum;

import java.util.Scanner;

public class ChamadosExercicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Descrição do chamado: ");
        String descricao = scanner.nextLine();
        Prioridade prioridade = null;
        while (prioridade == null) {
            System.out.print("Prioridade (BAIXA, MEDIA, ALTA ou URGENTE): ");
            String entrada = scanner.nextLine().trim().toUpperCase();

            try {
                prioridade = Prioridade.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridade inválida. Digite BAIXA, MEDIA, ALTA ou URGENTE.");
            }
        }
        
        Chamado chamado = new Chamado(descricao, prioridade);
        System.out.println("\n--- Chamado cadastrado ---");
        System.out.println("Descrição: " + chamado.getDescricao());
        System.out.println("Prioridade: " + chamado.getPrioridade());
        System.out.println("Orientação: " + chamado.obterMensagemPrioridade());

        scanner.close();
    }
}
