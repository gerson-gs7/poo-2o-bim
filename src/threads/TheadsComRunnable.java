package threads;

public class TheadsComRunnable {
    public static void main(String[] args) throws InterruptedException {
        Thread tarefa1 = new Thread(new Tarefa("Tarefa 1"));
        Thread tarefa2 = new Thread(new Tarefa("Tarefa 2"));
        Thread tarefa3 = new Thread(new Tarefa("Tarefa 3"));

        tarefa1.start();
        tarefa2.start();
        tarefa3.start();

        tarefa1.join();
        tarefa2.join();
        tarefa3.join();
        System.out.println("As três tarefas terminaram.");
    }

    private static class Tarefa implements Runnable {
        private final String nome;

        private Tarefa(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            for (int execucao = 1; execucao <= 5; execucao++) {
                System.out.println(nome + " - execução " + execucao);
                if (execucao < 5) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(nome + " foi interrompida.");
                        return;
                    }

                    
                }
            }
        }
    }
}
