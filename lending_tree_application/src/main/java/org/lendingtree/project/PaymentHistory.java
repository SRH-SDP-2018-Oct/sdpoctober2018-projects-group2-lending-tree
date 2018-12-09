package org.lendingtree.project;



import java.util.Date;
import java.util.Scanner;

public class PaymentHistory {

    private int inputCustomerId;
    private Double paymentAmount;
    private Date paymentDate;
    private String paymentType;
    private int paymentHistoryId;
    private String customerLastName;
    private String customerFirstName;
    private String inputYN;
    private int loanID;


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


    public void lenderPaymentHistory() {
        try {
            Scanner enterInput = new Scanner(System.in);
            System.out.println("Would you like to see all the data of every customer? Y/N?");
            inputYN = enterInput.nextLine();

            if (inputYN != null) {
                if (inputYN.equalsIgnoreCase("N")) {
                    System.out.println("You chose No");
                    System.out.println(" Enter the customer ID =  ");
                    inputCustomerId = Integer.parseInt(enterInput.nextLine());
                    PaymentHistoryDatabase.displayPaymentHistory(inputCustomerId);
                } else if (inputYN.equalsIgnoreCase("Y")) {
                    System.out.println(" You chose Yes");
                    PaymentHistoryDatabase.displayPaymentHistoryAll();
                } else {
                    System.out.println("Incorrect input! Please choose either Y or N");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void customerPaymentHistory() {
        int userId;

        try {
            userId = 1;
            PaymentHistoryDatabase.displayPaymentHistoryCustomerDatabase(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
        }


  /*public static void main (String[] args){
        PaymentHistory ph = new PaymentHistory();
        ph.lenderPaymentHistory();
    }*/


    }
}