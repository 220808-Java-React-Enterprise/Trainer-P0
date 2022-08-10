package com.revature.yolp.ui;

import com.revature.yolp.models.User;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;

import java.util.Scanner;

public class LoginMenu implements IMenu {

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to Yolp!");
                System.out.println("[1] Login");
                System.out.println("[2] Signup");
                System.out.println("[x] Exit!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        new MainMenu(user).start();
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void login() {
        System.out.println("\nNeeds implementation...");
    }

    private User signup() {
        String username = "";
        String password = "";
        User user = new User();
        UserService userService = new UserService();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreating account...");

        exit:
        {
            while (true) {
                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username: ");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter a password: ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

               confirmExit: {
                   while (true) {
                       System.out.println("\nIs this correct (y/n):");
                       System.out.println("Username: " + username + "\nPassword: " + password);
                       System.out.print("\nEnter: ");

                       switch (scan.nextLine().toLowerCase()) {
                           case "y":
                               user = new User(username, password);
                               return user;
                           case "n":
                               System.out.println("\nRestarting...");
                               break confirmExit;
                           default:
                               System.out.println("\nInvalid input!");
                               break;
                       }
                   }
               }
            }
        }
    }
}
