package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static int choice;

    public static void main(String[] args) {

        System.out.println("----Lending Tree: loan finding platform---- \n");

        boolean flag;

        do {
            Scanner input = new Scanner(System.in);

            System.out.println("\nWelcome, please select one of the following options:\n" +
                    "1) Customer portal\n" +
                    "2) Lender portal\n");
            choice = input.nextInt();
            flag = false;

            switch (choice) {

                case 1:
                    System.out.println("Dear customer, please select one of the following options:\n" +
                            "1) Login with an existing customer account\n" +
                            "2) Register a new customer account\n");
                    choice = input.nextInt();
                    flag = false;
                    switch (choice) {
                        case 1:
                            Customer currentUser = new Customer();
                            currentUser.login();
                            break;
                        case 2:
                            Customer newUser = new Customer();
                            try {
                                newUser.register();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    flag = false;
                    break;

                case 2:
                    System.out.println("Dear lender, please select one of the following options:\n" +
                            "1) Login with a representative account\n" +
                            "2) Register a new account\n");
                    choice = input.nextInt();
                    flag = false;
                    switch (choice) {
                        case 1:
                            Representative currentUser = new Representative();
                            currentUser.login();
                            break;
                        case 2:
                            System.out.println("Select what you want to register:\n" +
                                    "1) New institution\n" +
                                    "2) New representative\n");
                            choice = input.nextInt();
                            flag = false;
                            switch (choice) {
                                case 1:
                                    Representative newRepresentative = new Representative();
                                    try {
                                        newRepresentative.register();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    Institution newInstitution = new Institution();
                                    try {
                                        newInstitution.register();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                            }
                            flag = false;
                            break;

                        default:
                            System.out.println("Wrong Entry");
                            flag = true;
                            break;

                    }
            }
        }while (flag = false) ;
    }
}