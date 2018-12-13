package org.lendingtree.project;



import java.util.Date;
import java.util.Scanner;

public class PaymentHistory {

    private static int inputCustomerId;
    private Double paymentAmount;
    private Date paymentDate;
    private String paymentType;
    private int paymentHistoryId;
    private String customerLastName;
    private String customerFirstName;
    private static int inputYN;
    private int loanID;
    private static int inputRepresentativeID;
    private static int choice;
    private static Scanner input = new Scanner(System.in);

    public int getInputRepresentativeID() {
        return inputRepresentativeID;
    }

    public void setInputRepresentativeID(int inputRepresentativeID) {
        this.inputRepresentativeID = inputRepresentativeID;
    }


    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getInputCustomerId() {
        return inputCustomerId;
    }

    public void setInputCustomerId(int inputCustomerId) {
        this.inputCustomerId = inputCustomerId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public void setPaymentHistoryId(int paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    public static void userPaymentHistory(int userId, String userType) {

        try {

            if (userType == App.USER_TYPE_REPRESENTATIVE) {
                boolean flag;
                do {

                    System.out.println("Please select one of the following options:\n" +
                            "1) Display a specific customer's history\n" +
                            "2) Display the entire payment history\n"+
                            "3) Insert data into Payment History"+
                            "3) Exit");
                    choice = input.nextInt();
                    flag = false;
                    switch (choice) {
                        case 1:
                            System.out.println("You chose to display a specific customer's history");
                            System.out.println(" Enter the customer ID =  ");
                            inputCustomerId = InputValidationTools.inputNumber();
                            PaymentHistoryDatabase.displayPaymentHistoryRepresentativeSort(userId, inputCustomerId);
                            flag = false;
                            break;

                        case 2:
                            System.out.println(" You chose to display the entire payment history ");
                            PaymentHistoryDatabase.displayPaymentHistoryRepresentative(userId);
                            flag = false;
                            break;

                        case 3:
                            System.out.println(" You chose to exit");
                            flag = true;
                            break;

                        default:
                            System.out.println(("Wrong input. Please try again!"));
                            flag = false;
                            break;
                    }

                } while (flag == false);
            } else if (userType == App.USER_TYPE_CUSTOMER) {
                try {
                    System.out.println("Your records are as follows:");
                    PaymentHistoryDatabase.displayPaymentHistoryCustomer(userId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}





