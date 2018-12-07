package org.lendingtree.project;



import java.util.Date;
import java.util.Scanner;

public class PaymentHistory  {

    protected  int loanId;
    protected  int inputCustomerId;
    protected  Float paymentAmount;
    protected  Date paymentDate;
    protected  String paymentType;
    protected  int paymentHistoryId;





    public void lenderPaymentHistoryinput() {
        try {
            Scanner enterInput = new Scanner(System.in);
            System.out.println(" Enter the customer ID=");
            inputCustomerId = Integer.parseInt(enterInput.nextLine());
            PaymentDisplayDatabase.displayPaymentHistory(inputCustomerId);


        }catch (Exception e)
        {
            e.printStackTrace();
            }


 }

    public static void main (String[] args){
        PaymentHistory ph = new PaymentHistory();
        ph.lenderPaymentHistoryinput();
    }



}
