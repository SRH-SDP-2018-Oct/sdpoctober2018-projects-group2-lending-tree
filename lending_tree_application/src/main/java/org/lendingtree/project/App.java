package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    private static int currentSessionId;

    private static String userType;

    private static String choice;

    public static final String USER_TYPE_CUSTOMER = "customer";

    public static final String USER_TYPE_REPRESENTATIVE = "representative";

    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args){
        try {

            System.out.println("----Lending Tree: loan finding platform----");

            boolean flagMainPage = false;

            do {
                System.out.println("\nWelcome, please select one of the following options:\n" +
                        "1) Customer portal\n" +
                        "2) Lender portal\n");
                setChoice(input.next());

                switch (getChoice()) {

                    default:
                        System.out.println("\nWrong Entry, please try again.");
                        flagMainPage = true;
                        break;

                    case "1":
                        setUserType(USER_TYPE_CUSTOMER);
                        boolean flagCustomerPortal;

                        do {
                            flagCustomerPortal = false;
                            System.out.println("\nDear customer, please select one of the following options:\n" +
                                    "1) Login with an existing customer account\n" +
                                    "2) Register a new customer account\n" +
                                    "3) Go back\n");
                            setChoice(input.next());

                            switch (getChoice()) {

                                default:
                                    System.out.println("\nWrong Entry, please try again");
                                    flagCustomerPortal = true;
                                    break;

                                case "1":
                                    Customer currentUser = new Customer();
                                    setCurrentSessionId(currentUser.login().getId());
                                    if (currentUser != null) initializeDashboard();
                                    break;

                                case "2":
                                    Customer newUser = new Customer();
                                    try {
                                        newUser.register();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case "3":
                                    flagMainPage = true;
                                    break;
                            }
                        } while (flagCustomerPortal == true);
                        break;

                    case "2":
                        setUserType(USER_TYPE_REPRESENTATIVE);
                        boolean flagRepresentativePortal;
                        do {
                            flagRepresentativePortal = false;
                            System.out.println("Dear lender, please select one of the following options:\n" +
                                    "1) Login with a representative account\n" +
                                    "2) Register a new account\n" +
                                    "3) Go back\n");
                            setChoice(input.next());

                            switch (getChoice()) {

                                default:
                                    System.out.println("\nWrong Entry, please try again");
                                    flagRepresentativePortal = true;
                                    break;

                                case "1":
                                    Representative currentUser = new Representative();
                                    setCurrentSessionId(currentUser.login().getId());
                                    if (currentUser != null) initializeDashboard();
                                    break;

                                case "2":

                                    boolean flagRepresentativeRegistration;

                                    do {

                                        flagRepresentativeRegistration = false;

                                        System.out.println("Select what you want to register:\n" +
                                                "1) New institution\n" +
                                                "2) New representative\n" +
                                                "3) Go back\n");
                                        choice = input.next();

                                        switch (choice) {

                                            default:
                                                System.out.println("\nWrong Entry, please try again");
                                                flagRepresentativeRegistration = true;
                                                break;

                                            case "1":
                                                Institution newInstitution = new Institution();
                                                try {
                                                    newInstitution.register();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                                break;

                                            case "2":
                                                Representative newRepresentative = new Representative();
                                                try {
                                                    newRepresentative.register();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                                break;

                                            case "3":
                                                flagRepresentativePortal = true;
                                                break;
                                        }
                                    } while (flagRepresentativeRegistration == true);
                                    break;
                                case "3":
                                    flagMainPage = true;

                                    break;
                            }

                        } while (flagRepresentativePortal == true);
                        break;
                }

            } while (flagMainPage == true);

        } catch (Exception e){
            System.out.println("Program closed because of invalid input");
        }

    }

    public final static void initializeDashboard() throws SQLException {

        System.out.println("\n\n----Lending Tree: Dashboard\n");
        boolean flagDashboard;

        switch (getUserType()) {

            case USER_TYPE_CUSTOMER:
                do{
                    flagDashboard=false;
                    System.out.println("Please select one of the following options:\n" +
                            "1) See offers\n" +
                            "2) My loans\n" +
                            "3) My payment history\n" +
                            "4) My profile\n" +
                            "5) Settings\n");

                    choice = input.next();

                    switch (choice) {

                        default:
                            System.out.println("\nWrong Entry, please try again.");
                            flagDashboard = true;
                            break;

                        case "1":
                            Product.goMenuProduct(getCurrentSessionId(), getUserType());
                            break;
                        case "2":
                            Loan.goMenuLoan(getCurrentSessionId(), getUserType());
                            break;

                        case "3":
                            PaymentHistory.userPaymentHistory(getCurrentSessionId(), getUserType());
                            break;

                        case "4":
                            ProfileDatabase.getCustomerProfile(currentSessionId);
                            break;

                        case "5":
                            ProfileSettings.getSettings(getCurrentSessionId(), getUserType());
                            break;
                    }
                }while (flagDashboard == true);
                break;


            case USER_TYPE_REPRESENTATIVE:
                do {
                    flagDashboard=false;
                    System.out.println("Please select one of the following options:\n" +
                        "1) Manage products\n" +
                        "2) My loans\n" +
                        "3) My payment history\n" +
                        "4) My profile\n" +
                        "5) Settings\n");
                choice = input.next();

                switch (choice) {

                    default:
                        System.out.println("\nWrong Entry, please try again.");
                        flagDashboard = true;
                        break;

                    case "1":
                        Product.goMenuProduct(getCurrentSessionId(), getUserType());
                        break;

                    case "2":
                        Loan.goMenuLoan(getCurrentSessionId(), getUserType());
                        break;

                    case "3":
                        PaymentHistory.userPaymentHistory(getCurrentSessionId(), getUserType());
                        break;

                    case "4":
                        ProfileDatabase.getRepresentativeProfile(getCurrentSessionId());
                        break;

                    case "5":
                        ProfileSettings.getSettings(getCurrentSessionId(), getUserType());
                        break;
                }
                }while(flagDashboard == true);
                break;
        }

    }

    public static String getChoice() {

        return choice;
    }

    public static void setChoice(String choice) {

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