package com.main;

import com.menu.MenuHandler;
import com.service.BankService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        MenuHandler menuHandler = new MenuHandler(bankService);
        bankService.autoFillRatesOfExchange();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                menuHandler.showMainMenu();
                String choice = sc.nextLine();
                try {
                    switch (choice) {
                        case "1":
                            menuHandler.userManagementMenu(sc);
                            break;
                        case "2":
                            menuHandler.accountManagementMenu(sc);
                            break;
                        case "3":
                            menuHandler.exchangeRateManagementMenu(sc);
                            break;
                        case "4":
                            menuHandler.transactionManagementMenu(sc);
                            break;
                        case "5":
                            menuHandler.accountOperationsMenu(sc);
                            break;
                        case "6":
                            menuHandler.autoFillDataMenu(sc);
                            break;
                        case "7":
                            return;
                        default:
                            System.out.println("Invalid option, please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } finally {
            sc.close();
            bankService.closeAll();
        }
    }
}
