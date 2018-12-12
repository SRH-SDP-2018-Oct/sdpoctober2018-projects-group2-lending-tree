package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static int currentSessionId;

    public static String userType;

    public static int choice;

    public static final String USER_TYPE_CUSTOMER = "customer";

    public static final String USER_TYPE_REPRESENTATIVE = "representative";

    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {



        System.out.println("----Lending Tree: loan finding platform----");

        boolean flag;

        do {
            System.out.println("\nWelcome, please select one of the following options:\n" +
                    "1) Customer portal\n" +
                    "2) Lender portal\n");
            setChoice(input.nextInt());
            flag=false;

            switch (getChoice()) {

                case 1:
                    setUserType(USER_TYPE_CUSTOMER);
                    System.out.println("Dear customer, please select one of the following options:\n" +
                            "1) Login with an existing customer account\n" +
                            "2) Register a new customer account\n");
                    setChoice(input.nextInt());

                    switch (getChoice()) {

                        case 1:
                            Customer currentUser = new Customer();
                            setCurrentSessionId(currentUser.login().getId());
                            setUserType(USER_TYPE_CUSTOMER);
                            initializeDashboard();
                            break;

                        case 2:
                            Customer newUser = new Customer();
                            try {
                                newUser.register();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                    } break;

                case 2:
                    System.out.println("Dear lender, please select one of the following options:\n" +
                            "1) Login with a representative account\n" +
                            "2) Register a new account\n");
                    setChoice(input.nextInt());

                    switch (getChoice()) {

                        case 1:
                            Representative currentUser = new Representative();
                            setCurrentSessionId(currentUser.login().getId());
                            setUserType(USER_TYPE_REPRESENTATIVE);
                            initializeDashboard();
                            break;

                        case 2:
                            System.out.println("Select what you want to register:\n" +
                                    "1) New institution\n" +
                                    "2) New representative\n");
                            choice = input.nextInt();

                            switch (choice) {

                                case 1:
                                    Institution newInstitution = new Institution();
                                    try {
                                        newInstitution.register();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case 2:
                                    Representative newRepresentative = new Representative();
                                    try {
                                        newRepresentative.register();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }

                            break;

                        default:
                            System.out.println("Wrong Entry");
                            flag=true;
                            break;
                    } break;
            }

        } while (flag == true);

    }

    public final static void initializeDashboard() {

        System.out.println("\n\n----Lending Tree: Dashboard\n");

        switch (getUserType()) {

            case USER_TYPE_CUSTOMER:
                System.out.println("Please select one of the following options:\n" +
                        "1) Search offers\n" +
                        "2) My loans\n" +
                        "3) My payment history\n" +
                        "4) My profile\n" +
                        "5) Settings\n");
                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("Wait for it");
                        break;

                    case 2:
                        Loan.goMenuLoan(getCurrentSessionId(), getUserType());
                        break;

                    case 3:
                        PaymentHistory.customerPaymentHistory(getCurrentSessionId());
                        break;

                    case 4:
                        try {
                            ProfileDatabase.getCustomerProfile(currentSessionId);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 5:
                        try {
                            ProfileSettingDatabase.getCustomerSettings(getCurrentSessionId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                }

            case USER_TYPE_REPRESENTATIVE:

                System.out.println("Please select one of the following options:\n" +
                        "1) Manage products\n" +
                        "2) My loans\n" +
                        "3) My payment history\n" +
                        "4) My profile\n" +
                        "5) Settings\n");
                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("Wait for it");
                        break;

                    case 2:
                        Loan.goMenuLoan(getCurrentSessionId(), getUserType());
                        break;

                    case 3:
                        PaymentHistory.lenderPaymentHistory();
                        break;

                    case 4:
                        try {
                            ProfileDatabase.getInstitutionProfile(getCurrentSessionId()); //take a look
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 5:
                        try {
                            ProfileSettingDatabase.getInstitutionSettings(getCurrentSessionId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                }
        }

    }

    public static int getChoice() {

        return choice;
    }

    public static void setChoice(int choice) {

        App.choice = choice;
    }

    public static int getCurrentSessionId() {

        return currentSessionId;
    }

    public static void setCurrentSessionId(int currentSessionId) {

        App.currentSessionId = currentSessionId;
    }

    public static String getUserType() {

        return userType;
    }

    public static void setUserType(String userType) {

        App.userType = userType;
    }

}