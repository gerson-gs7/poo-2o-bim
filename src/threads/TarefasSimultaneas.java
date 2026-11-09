package threads;

public class TarefasSimultaneas {
    public static void main(String[] args) throws InterruptedException {
        Thread numeros = new ThreadNumeros();
        Thread letras = new ThreadLetras();

        numeros.start();
        letras.start();

        numeros.join();
        letras.join();
        System.out.println("As duas threads terminaram.");
    }

    private static class ThreadNumeros extends Thread {
        @Override
        public void run() {
            for (int numero = 1; numero <= 10; numero++) {
                System.out.println("Thread 1 - número: " + numero);
            }
        }
    }

    private static class ThreadLetras extends Thread {
        @Override
        public void run() {
            for (char letra = 'A'; letra <= 'J'; letra++) {
                System.out.println("Thread 2 - letra: " + letra);
            }
        }
    }
}
