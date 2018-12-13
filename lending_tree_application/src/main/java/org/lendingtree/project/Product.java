package org.lendingtree.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Product {

    private int productId;
    private int productTypeId;
    private String productDescription;
    private int representativeId;
    private Double productAmount;
    private Double productInterestRate;
    private int productNumberOfPayments;
    private String productAvailabilityFrom;
    private String productAvailabilityTo;
    private boolean productActiveStatus;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public Double getProductInterestRate() {
        return productInterestRate;
    }

    public void setProductInterestRate(Double productInterestRate) {
        this.productInterestRate = productInterestRate;
    }

    public int getProductNumberOfPayments() {
        return productNumberOfPayments;
    }

    public void setProductNumberOfPayments(int productNumberOfPayments) {
        this.productNumberOfPayments = productNumberOfPayments;
    }

    public String getProductAvailabilityFrom() {
        return productAvailabilityFrom;
    }

    public void setProductAvailabilityFrom(String productAvailabilityFrom) {
        this.productAvailabilityFrom = productAvailabilityFrom;
    }

    public String getProductAvailabilityTo() {
        return productAvailabilityTo;
    }

    public void setProductAvailabilityTo(String productAvailabilityTo) {
        this.productAvailabilityTo = productAvailabilityTo;
    }

    public boolean getProductActiveStatus() {
        return productActiveStatus;
    }

    public void setProductActiveStatus(boolean productActiveStatus) {
        this.productActiveStatus = productActiveStatus;
    }

    public static String MESSAGE_VALID_NUMBER = "Please select a valid number: ";
    public static String MESSAGE_YOU_ENTERED = "You entered the number: ";


    public static void goMenuProduct(int userId, String userType) {
        try {
            Product product = new Product();
            String choice;
            ArrayList<Integer> productIds = new ArrayList<>();

            System.out.println("----Lending Tree: Products menu\n");

            boolean flag;
            boolean flagFilters;

            Scanner input = new Scanner(System.in);

            switch (userType) {
                default:
                    flag = true;
                    System.out.println("Invalid entry.\n");
                    break;

                case App.USER_TYPE_CUSTOMER:
                    do {
                        flag = false;
                        System.out.println("\nPlease select one of the following options:\n" +
                                "1) Search for products\n" +
                                "2) See \"My list\"\n" +
                                "3) Exit\n");
                        choice = input.next();

                        switch (choice) {
                            default:
                                System.out.println("Invalid entry.\n");
                                break;

                            case "1":
                                System.out.println("\nPlease select one of the following options:\n" +
                                        "1) Search all products\n" +
                                        "2) Search with filters\n");
                                choice = input.next();

                                switch (choice) {
                                    default:
                                        System.out.println("Invalid entry.\n");
                                        break;

                                    case "1":
                                        product.setProductActiveStatus(true);
                                        ProductDatabase.getAllActiveProducts(product, false);

                                        productIds = (addProductsToMyList(input, productIds));

                                        break;

                                    case "2":
                                        do {
                                            System.out.println("\nPlease select one of the following options:\n" +
                                                    "1) Enter filter by description\n" +
                                                    "2) Enter filter by amount\n" +
                                                    "3) Enter filter by interest rate\n" +
                                                    "4) Enter filter by number of payments\n" +
                                                    "5) Search with the current filters\n");

                                            choice = input.next();
                                            flagFilters = false;

                                            switch (choice) {
                                                default:
                                                    System.out.println("Invalid entry.\n");
                                                    break;

                                                case "1":
                                                    System.out.println("\nPlease enter the desired description:\n" +
                                                            "(i.e., \"construction\")\n" +
                                                            "(If you do not want to filter by description, press Enter)\n");
                                                    input.nextLine();
                                                    String inputDescription = input.nextLine();
                                                    if (!inputDescription.trim().isEmpty()) {
                                                        product.setProductDescription(inputDescription);
                                                    }
                                                    break;

                                                case "2":
                                                    System.out.println("\nPlease enter the minimum amount:\n" +
                                                            "(i.e., \"1000\")\n" +
                                                            "(If you do not want to filter by amount, press Enter)\n");
                                                    product.setProductAmount(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED));
                                                    break;

                                                case "3":
                                                    System.out.println("\nPlease enter the maximum interest rate:\n" +
                                                            "(i.e., \"1.5\")\n" +
                                                            "(If you do not want to filter by interest rate, press Enter)\n");
                                                    product.setProductInterestRate(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED));
                                                    break;

                                                case "4":
                                                    System.out.println("\nPlease enter the minimum number of payments:\n" +
                                                            "(i.e., \"12\")\n" +
                                                            "(If you do not want to filter by number of payments, press Enter)\n");
                                                    product.setProductNumberOfPayments(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());
                                                    break;

                                                case "5":
                                                    flagFilters = true;
                                                    product.setProductActiveStatus(true);
                                                    ProductDatabase.getAllActiveProducts(product, true);

                                                    productIds = (addProductsToMyList(input, productIds));

                                                    break;

                                            }
                                        } while (!flagFilters);
                                }
                                break;
                            case "2":
                                if (!productIds.isEmpty()) {
                                    ProductDatabase.getProductDetails(productIds, true);
                                    System.out.println("\nDo you want to send a request for a product?\n" +
                                            "1) Yes\n" +
                                            "2) No\n");
                                    choice = input.next();

                                    switch (choice) {
                                        default:
                                            System.out.println("Invalid entry.\n");
                                            break;

                                        case "1":
                                            System.out.println("\nPlease enter the product ID.");
                                            choice = InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).toString();
                                            Date date = new Date();
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(date);

                                            String todaysDate = calendar.get(Calendar.YEAR) + "-" +
                                                    (calendar.get(Calendar.MONTH) + 1) + "-" +
                                                    calendar.get(Calendar.DAY_OF_MONTH);

                                            Loan newLoan = new Loan();
                                            newLoan.setCustomerId(userId);
                                            newLoan.setProductId(Double.valueOf(choice).intValue());
                                            newLoan.setLoanStatusId(1);
                                            newLoan.setLoanDateApplied(todaysDate);
                                            LoanDatabase.insertLoan(newLoan);

                                            System.out.println("\nYou applied to the product " + newLoan.getProductId() + " successfully.");
                                            break;

                                        case "2":
                                            break;
                                    }

                                } else {
                                    System.out.println("\n\"My list\" is empty.");
                                }
                                break;

                            case "3":
                                flag = true;
                                break;
                        }
                    } while (!flag);
                    break;

                case App.USER_TYPE_REPRESENTATIVE:
                    do {

                        System.out.println("\nPlease select one of the following options:\n" +
                                "1) Create a product\n" +
                                "2) List my products\n" +
                                "3) Exit\n");
                        choice = input.next();

                        switch (choice) {
                            default:
                                flag = false;
                                System.out.println("Invalid entry.\n");
                                break;

                            case "1":
                                flag = false;

                                System.out.println("\nPlease select the product type.\n" +
                                        "The product types are the following:\n");
                                ProductDatabase.getAllProductTypeDescription();
                                product.setProductTypeId(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());

                                System.out.println("\nPlease enter the description:\n" +
                                        "(e.g., \"Recommended for students\")\n");
                                input.nextLine();
                                product.setProductDescription(input.nextLine());

                                product.setRepresentativeId(userId);

                                System.out.println("\nPlease enter the amount:\n" +
                                        "(e.g., \"3000\")\n");
                                product.setProductAmount(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED));

                                System.out.println("\nPlease enter the interest rate:\n" +
                                        "(e.g., \"3.5\")\n");
                                product.setProductInterestRate(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED));

                                System.out.println("\nPlease enter the number of payments:\n" +
                                        "(e.g., \"12\")\n");
                                product.setProductNumberOfPayments(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());

                                System.out.println("\nPlease enter the starting date:\n" +
                                        "(e.g., \"2018-05-30\")\n");
                                product.setProductAvailabilityFrom(input.nextLine());

                                System.out.println("\nPlease enter the ending date:\n" +
                                        "(e.g., \"2018-08-30\")\n");
                                product.setProductAvailabilityTo(input.nextLine());

                                product.setProductActiveStatus(true);

                                ProductDatabase.createProduct(product);

                                System.out.println("\nProduct created successfully.\n");
                                break;

                            case "2":
                                flag = false;

                                System.out.println("\nPlease select one of the following options:\n" +
                                        "1) List representative's products\n" +
                                        "2) List institution's products\n");
                                choice = input.next();

                                switch (choice) {
                                    default:
                                        System.out.println("Invalid entry.\n");
                                        break;

                                    case "1":
                                        ProductDatabase.getProductFromRepresentative(userId, false);
                                        System.out.println("\nWould you like to modify a product?\n" +
                                                "1) Update the description\n" +
                                                "2) Disable the product\n" +
                                                "3) Cancel\n");
                                        choice = input.next();

                                        switch (choice) {
                                            default:
                                                System.out.println("Invalid entry.\n");
                                                break;

                                            case "1":
                                                System.out.println("\nEnter the ID of the product:\n");
                                                product.setProductId(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());
                                                System.out.println("\nEnter the new description:\n");
                                                input.nextLine();
                                                product.setProductDescription(input.nextLine());
                                                product.setRepresentativeId(userId);

                                                ProductDatabase.updateProduct(product);
                                                System.out.println("\nDescription updated successfully.");
                                                break;

                                            case "2":
                                                System.out.println("\nEnter the ID of the product:\n");
                                                product.setProductId(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());
                                                product.setProductActiveStatus(false);
                                                product.setRepresentativeId(userId);

                                                ProductDatabase.disableProduct(product);
                                                System.out.println("\nProduct disabled successfully.");
                                                break;

                                            case "3":
                                                break;
                                        }
                                        break;

                                    case "2":
                                        ProductDatabase.getProductFromRepresentative(userId, true);
                                        break;
                                }
                                break;

                            case "3":
                                flag = true;
                                break;
                        }
                    } while (!flag);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Integer> addProductsToMyList(Scanner input, ArrayList<Integer> productIds) {
        String choice;
        boolean flagAddProducts;

        do {
            System.out.println("\nWould you like to add a product to \"My list\"?\n" +
                    "(Products in \"My list\" are displayed with detail \n" +
                    "and can be requested)\n\n" +
                    "1) Yes\n" +
                    "2) No\n");
            choice = input.next();
            flagAddProducts = false;

            switch (choice) {
                default:
                    System.out.println("Invalid entry.\n");
                    break;

                case "1":
                    System.out.println("\nEnter the ID of the product:\n");
                    productIds.add(InputValidationTools.inputDouble(MESSAGE_VALID_NUMBER, MESSAGE_YOU_ENTERED).intValue());
                    System.out.println("\nCurrent IDs in the list:\n");
                    for (int i = 0; i < productIds.size(); i++) {
                        System.out.println((i + 1) + ") " + productIds.get(i));
                    }
                    break;

                case "2":
                    flagAddProducts = true;
                    break;

            }
        } while (!flagAddProducts);
        return productIds;
    }

}
