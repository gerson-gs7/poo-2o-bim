package tiposEnumeradosEnum;

import java.util.Scanner;
import java.util.Locale;

public class NivelAcessoExercicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        NivelAcesso nivel = null;
        while (nivel == null) {
            System.out.print("Nível de acesso (ADMINISTRADOR, GERENTE ou USUARIO): ");
            String entrada = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            try {
                nivel = NivelAcesso.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Nível inválido. Digite ADMINISTRADOR, GERENTE ou USUARIO.");
            }
        }

        System.out.println("Permissões: " + obterPermissoes(nivel));
        scanner.close();
    }

    private static String obterPermissoes(NivelAcesso nivel) {
        switch (nivel) {
            case ADMINISTRADOR:
                return "acesso total ao sistema";
            case GERENTE:
                return "gerenciar operações e consultar relatórios";
            case USUARIO:
                return "acessar recursos básicos";
            default:
                throw new IllegalStateException("Nível de acesso não suportado: " + nivel);
        }
    }
}
