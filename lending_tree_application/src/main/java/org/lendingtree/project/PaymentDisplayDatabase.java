package org.lendingtree.project;

import java.sql.*;

public class PaymentDisplayDatabase extends PaymentHistory {


    protected static Connection databaseConnection = ConnectMSSQLServer.getConnection();

        public static void displayPaymentHistory(int inputCustomerId){
            PaymentHistory pm = new PaymentHistory();
            try {
                String getPaymentHistory = " SELECT * FROM PAYMENT_HISTORY INNER JOIN LOAN ON PAYMENT_HISTORY.LOAN_ID= LOAN.LOAN_ID WHERE customer_id = ?";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistory);
                dbPreparedStatement.setInt(1, inputCustomerId);
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                System.out.println("Loan ID \t Payment Amount \t Payment Date \t Payment Type \t Payment History ID");
                while (dbResultSet.next()) {
                    pm.loanId = dbResultSet.getShort("loan_id");
                    pm.paymentAmount = dbResultSet.getFloat("payment_amount");
                    pm.paymentDate = dbResultSet.getDate("payment_date");
                    pm.paymentType = dbResultSet.getString("payment_type");
                    pm.paymentHistoryId = dbResultSet.getShort("payment_history_Id");


                    System.out.println(pm.inputCustomerId + "\t\t\t " + pm.paymentAmount + "\t\t\t\t " + pm.paymentDate + "\t\t " +  pm.paymentType + "\t\t\t " +  pm.paymentHistoryId);
                }
            }catch (Exception e){

            }


    }
    public static void main (String[] args) {
        PaymentDisplayDatabase ph = new PaymentDisplayDatabase();
        ph.displayPaymentHistory(1);
    }


   // public static void checkInputID( ) throws SQLException {
   //    String checkIfIdExists = "SELECT * FROM PAYMENT_HISTORY WHERE" +inputLoanId+"=?";
   //    PreparedStatement checkInputId = databaseConnection.prepareStatement(checkIfIdExists);
//
//  }
}
