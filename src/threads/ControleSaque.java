package threads;

public class ControleSaque {
    public static void main(String[] args) throws InterruptedException {
        ContaBancaria conta = new ContaBancaria(1000.00);
        Thread saque1 = new Thread(() -> conta.sacar(700.00), "Saque 1");
        Thread saque2 = new Thread(() -> conta.sacar(700.00), "Saque 2");

        saque1.start();
        saque2.start();

        saque1.join();
        saque2.join();
        System.out.printf("Saldo final: R$ %.2f%n", conta.getSaldo());
    }

    private static class ContaBancaria {
        private double saldo;

        private ContaBancaria(double saldoInicial) {
            this.saldo = saldoInicial;
        }

        public synchronized void sacar(double valor) {
            if (valor > saldo) {
                System.out.println(Thread.currentThread().getName()
                        + " - saque recusado: saldo insuficiente.");
                return;
            }

            saldo -= valor;
            System.out.printf("%s - saque de R$ %.2f realizado.%n",
                    Thread.currentThread().getName(), valor);
        }

        public synchronized double getSaldo() {
            return saldo;
        }
    }
}
