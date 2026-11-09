package threads;

public class ProcessamentoPedidos {
    public static void main(String[] args) throws InterruptedException {
        Thread pedido1 = new Thread(new Pedido("Pedido-001", 2000));
        Thread pedido2 = new Thread(new Pedido("Pedido-002", 3000));
        Thread pedido3 = new Thread(new Pedido("Pedido-003", 4000));

        pedido1.start();
        pedido2.start();
        pedido3.start();

        pedido1.join();
        pedido2.join();
        pedido3.join();
        System.out.println("Todos os pedidos foram processados.");
    }

    private static class Pedido implements Runnable {
        private final String identificacao;
        private final int tempoProcessamentoMs;

        private Pedido(String identificacao, int tempoProcessamentoMs) {
            this.identificacao = identificacao;
            this.tempoProcessamentoMs = tempoProcessamentoMs;
        }

        @Override
        public void run() {
            System.out.println(identificacao + " - início do processamento.");
            System.out.println(identificacao + " - tempo estimado: "
                    + tempoProcessamentoMs / 1000.0 + " segundos.");
            try {
                Thread.sleep(tempoProcessamentoMs);
                System.out.println(identificacao + " - pedido finalizado.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(identificacao + " - processamento interrompido.");
            }
        }
    }
}
