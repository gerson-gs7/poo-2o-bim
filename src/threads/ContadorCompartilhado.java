package threads;

public class ContadorCompartilhado {
    private static final int LIMITE = 1000;

    public static void main(String[] args) throws InterruptedException {
        Contador contadorSemSincronizacao = new Contador();
        executarDuasThreads(contadorSemSincronizacao, false);
        System.out.println("Resultado sem sincronização: " + contadorSemSincronizacao.getValor());

        Contador contadorSincronizado = new Contador();
        executarDuasThreads(contadorSincronizado, true);
        System.out.println("Resultado com synchronized: " + contadorSincronizado.getValor());
    }

    private static void executarDuasThreads(Contador contador, boolean sincronizado)
            throws InterruptedException {
        Runnable tarefa = () -> {
            while (sincronizado
                    ? contador.sincronizado(LIMITE)
                    : contador.semSincronizacao(LIMITE)) {
            }
        };

        Thread thread1 = new Thread(tarefa, "Contador 1");
        Thread thread2 = new Thread(tarefa, "Contador 2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static class Contador {
        private volatile int valor;

        public int getValor() {
            return valor;
        }

        public boolean semSincronizacao(int limite) {
            if (valor >= limite) {
                return false;
            }

            Thread.yield();
            valor++;
            return true;
        }

        public synchronized boolean sincronizado(int limite) {
            if (valor >= limite) {
                return false;
            }

            valor++;
            return true;
        }
    }
}
