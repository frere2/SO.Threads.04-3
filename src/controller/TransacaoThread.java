package controller;

import model.ContaBancaria;

import java.util.concurrent.Semaphore;

public class TransacaoThread extends Thread {
    private static Semaphore saquesSemaforo = new Semaphore(1);
    private static Semaphore depositosSemaforo = new Semaphore(1);
    private static Semaphore contaSemaforo = new Semaphore(1);

    private ContaBancaria conta;
    private boolean isSaque;
    private double valor;

    public TransacaoThread(ContaBancaria conta, boolean isSaque, double valor) {
        this.conta = conta;
        this.isSaque = isSaque;
        this.valor = valor;
    }

    @Override
    public void run() {
        try {
            if (isSaque) {
                saquesSemaforo.acquire();
                contaSemaforo.acquire();
                System.out.println("Processando saque na conta " + conta.getCodigoConta());
                if (valor <= conta.getSaldo()) {
                    double novoSaldo = conta.getSaldo() - valor;
                    conta.setSaldo(novoSaldo);
                    System.out.println("Saque de R$" + valor + " realizado. Saldo atual: R$" + novoSaldo);
                } else {
                    System.out.println("Saldo insuficiente para saque de R$" + valor + ". Saldo atual: R$" + conta.getSaldo());
                }
                contaSemaforo.release();
                saquesSemaforo.release();
            } else {
                depositosSemaforo.acquire();
                contaSemaforo.acquire();
                System.out.println("Processando depósito na conta " + conta.getCodigoConta());
                double novoSaldo = conta.getSaldo() + valor;
                conta.setSaldo(novoSaldo);
                System.out.println("Depósito de R$" + valor + " realizado. Saldo atual: R$" + novoSaldo);
                contaSemaforo.release();
                depositosSemaforo.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}