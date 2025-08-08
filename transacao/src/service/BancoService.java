package service;

import model.Conta;
import java.util.ArrayList;
import java.util.List;

public class BancoService {
    private List<Conta> accounts = new ArrayList<>();

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        accounts.add(new Conta(accountNumber, accountHolder, initialBalance));
        System.out.println("Conta criada com sucesso!");
    }

    public Conta searchAccount(String accountNumber) {
        for (Conta account : accounts) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void transfer(String originAccountNumber, String destinationAccountNumber, double amount) {
        Conta origin = searchAccount(originAccountNumber);
        Conta destination = searchAccount(destinationAccountNumber);

        if (origin == null || destination == null) {
            System.out.println("Conta de origem ou destino não encontrada.");
            return;
        }

        if (origin.withdraw(amount)) {
            destination.deposit(amount);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
}

