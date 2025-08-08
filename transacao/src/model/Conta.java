package model;

public class Conta {
    private String number;
    private String holder;
    private double balance;

    public Conta(String number, String holder, double initialBalance) {
        this.number = number;
        this.holder = holder;
        this.balance = initialBalance;
    }

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

