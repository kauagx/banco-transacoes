package util;

import service.BancoService;
import java.util.Scanner;

public class Menu {
    private BancoService banco;
    private Scanner scanner;

    public Menu() {
        banco = new BancoService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== MENU BANCO ===");
            System.out.println("1. Criar conta");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> createAccount();
                case 2 -> checkBalance();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> transfer();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void createAccount() {
        System.out.print("Número da conta: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Titular: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();
        banco.createAccount(accountNumber, accountHolder, initialBalance);
    }

    private void checkBalance() {
        System.out.print("Número da conta: ");
        String accountNumber = scanner.nextLine();
        var account = banco.searchAccount(accountNumber);
        if (account != null) {
            System.out.println("Saldo: R$ " + account.getBalance());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void deposit() {
        System.out.print("Número da conta: ");
        String accountNumber = scanner.nextLine();
        var account = banco.searchAccount(accountNumber);
        if (account != null) {
            System.out.print("Valor do depósito: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void withdraw() {
        System.out.print("Número da conta: ");
        String accountNumber = scanner.nextLine();
        var account = banco.searchAccount(accountNumber);
        if (account != null) {
            System.out.print("Valor do saque: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // limpa buffer
            if (account.withdraw(amount)) {
                System.out.println("Saque realizado.");
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void transfer() {
        System.out.print("Conta origem: ");
        String originAccount = scanner.nextLine();
        System.out.print("Conta destino: ");
        String destinationAccount = scanner.nextLine();
        System.out.print("Valor: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        banco.transfer(originAccount, destinationAccount, amount);
    }
}
