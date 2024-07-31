package com.service;

import com.dao.UserDAO;
import com.entity.User;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(Scanner sc) {
        try {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();
            System.out.print("Enter user address: ");
            String address = sc.nextLine();
            User user = new User();
            user.setName(name);
            user.setAddress(address);
            userDAO.addUser(user);
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public void deleteUser(Scanner sc) {
        try {
            System.out.print("Enter user ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            User user = userDAO.getUserById(id);
            if (user != null) {
                userDAO.deleteUser(user);
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void updateUser(Scanner sc) {
        try {
            System.out.print("Enter user ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            User user = userDAO.getUserById(id);
            if (user != null) {
                System.out.print("Enter new user name: ");
                user.setName(sc.nextLine());
                System.out.print("Enter new user address: ");
                user.setAddress(sc.nextLine());
                userDAO.updateUser(user);
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void viewAllUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            users.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error viewing users: " + e.getMessage());
        }
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public void close() {
        userDAO.close();
    }
}
