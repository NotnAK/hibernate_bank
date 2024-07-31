package com.menu;


import com.service.BankService;

import java.util.Scanner;

public class MenuHandler {
    private BankService bankService;

    public MenuHandler(BankService bankService) {
        this.bankService = bankService;
    }

    public void showMainMenu() {
        System.out.println("1: User Management");
        System.out.println("2: Account Management");
        System.out.println("3: Exchange Rate Management");
        System.out.println("4: Transaction Management");
        System.out.println("5: Account Operations");
        System.out.println("6: Auto-fill Data");
        System.out.println("7: Exit");
        System.out.print("-> ");
    }

    public void userManagementMenu(Scanner sc) {
        while (true) {
            System.out.println("1: Add User");
            System.out.println("2: Delete User");
            System.out.println("3: Update User");
            System.out.println("4: View all Users");
            System.out.println("5: Back to Main Menu");
            System.out.print("-> ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        bankService.addUser(sc);
                        break;
                    case "2":
                        bankService.deleteUser(sc);
                        break;
                    case "3":
                        bankService.updateUser(sc);
                        break;
                    case "4":
                        bankService.viewAllUsers();
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void accountManagementMenu(Scanner sc) {
        while (true) {
            System.out.println("1: Add Account");
            System.out.println("2: Delete Account");
            System.out.println("3: Update Account");
            System.out.println("4: View all Accounts");
            System.out.println("5: Back to Main Menu");
            System.out.print("-> ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        bankService.addAccount(sc);
                        break;
                    case "2":
                        bankService.deleteAccount(sc);
                        break;
                    case "3":
                        bankService.updateAccount(sc);
                        break;
                    case "4":
                        bankService.viewAllAccounts();
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void exchangeRateManagementMenu(Scanner sc) {
        while (true) {
            System.out.println("1: Add Rate of Exchange");
            System.out.println("2: Delete Rate of Exchange");
            System.out.println("3: Update Rate of Exchange");
            System.out.println("4: View all Rates of Exchange");
            System.out.println("5: Back to Main Menu");
            System.out.print("-> ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        bankService.addRateOfExchange(sc);
                        break;
                    case "2":
                        bankService.deleteRateOfExchange(sc);
                        break;
                    case "3":
                        bankService.updateRateOfExchange(sc);
                        break;
                    case "4":
                        bankService.viewAllRatesOfExchange();
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void transactionManagementMenu(Scanner sc) {
        while (true) {
            System.out.println("1: Add Transaction");
            System.out.println("2: Delete Transaction");
            System.out.println("3: Update Transaction");
            System.out.println("4: View all Transactions");
            System.out.println("5: Back to Main Menu");
            System.out.print("-> ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        bankService.addTransaction(sc);
                        break;
                    case "2":
                        bankService.deleteTransaction(sc);
                        break;
                    case "3":
                        bankService.updateTransaction(sc);
                        break;
                    case "4":
                        bankService.viewAllTransactions();
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void accountOperationsMenu(Scanner sc) {
        while (true) {
            System.out.println("1: Deposit Funds");
            System.out.println("2: Transfer Funds");
            System.out.println("3: Convert Currency");
            System.out.println("4: Get Total Funds in UAH");
            System.out.println("5: Back to Main Menu");
            System.out.print("-> ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        bankService.depositFunds(sc);
                        break;
                    case "2":
                        bankService.transferFunds(sc);
                        break;
                    case "3":
                        bankService.convertCurrency(sc);
                        break;
                    case "4":
                        bankService.getTotalFundsInUAH(sc);
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void autoFillDataMenu(Scanner sc) {
        bankService.autoFillUsers(2);}
}
