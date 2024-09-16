package view;

import controller.TransacaoThread;
import model.ContaBancaria;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(12345, 1000.0);
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            boolean isSaque = random.nextBoolean();
            double valor = 50 + random.nextInt(451);

            TransacaoThread transacao = new TransacaoThread(conta, isSaque, valor);
            transacao.start();
        }
    }
}
