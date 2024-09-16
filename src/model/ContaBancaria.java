package model;

public class ContaBancaria {
    private int codigoConta;
    private double saldo;

    public ContaBancaria(int codigoConta, double saldoInicial) {
        this.codigoConta = codigoConta;
        this.saldo = saldoInicial;
    }

    public int getCodigoConta() {
        return codigoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}