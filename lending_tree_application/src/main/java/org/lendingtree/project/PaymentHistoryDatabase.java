package org.lendingtree.project;

import java.sql.*;

public class PaymentHistoryDatabase {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String PAYMENT_AMOUNT = "payment_amount";
    private static final String PAYMENT_DATE = "payment_date";
    private static final String PAYMENT_TYPE = "payment_type";
    private static final String PAYMENT_HISTORY_ID = "payment_history_id";
    private static final String CUSTOMER_LAST_NAME = "customer_last_name";
    private static final String CUSTOMER_FIRST_NAME = "customer_first_name";
    private static final String PAYMENT_HISTORY = "payment_history";
    private static final String LOAN_ID = "loan_id";
    private static final String LOAN = "loan";
    private static final String CUSTOMER = "customer";

    protected static Connection databaseConnection = ConnectMSSQLServer.getConnection();

        public static void displayPaymentHistoryAll(){
            PaymentHistory newPaymentHistory = new PaymentHistory();
            try {
                String getPaymentHistoryForAll = " SELECT * FROM " +PAYMENT_HISTORY+
                                                 " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+
                                                 " . " +LOAN_ID+ " = " +LOAN+ " . " +LOAN_ID+
                                                 " INNER JOIN " +CUSTOMER+ " ON " +LOAN+ " . "
                                                 +CUSTOMER_ID+ " = " +CUSTOMER+ " . " +CUSTOMER_ID+ "";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistoryForAll);
                Boolean availableData = false;
                int loopCount =0;
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                System.out.println("Loan ID \t Payment Amount \t Payment Date \t Payment Type \t Payment History ID");
                while (dbResultSet.next()) {
                    newPaymentHistory.setInputCustomerId(dbResultSet.getInt(CUSTOMER_ID));
                    newPaymentHistory.setCustomerLastName(dbResultSet.getString (CUSTOMER_LAST_NAME));
                    newPaymentHistory.setCustomerFirstName(dbResultSet.getString (CUSTOMER_FIRST_NAME));
                    newPaymentHistory.setPaymentAmount(dbResultSet.getDouble(PAYMENT_AMOUNT));
                    newPaymentHistory.setPaymentDate(dbResultSet.getDate(PAYMENT_DATE));
                    newPaymentHistory.setPaymentType(dbResultSet.getString(PAYMENT_TYPE));
                    newPaymentHistory.setPaymentHistoryId(dbResultSet.getShort(PAYMENT_HISTORY_ID));

                    loopCount++;
                    System.out.println("----------" + loopCount + "----------");
                    System.out.println("CUSTOMER ID: " + "" + dbResultSet.getInt(CUSTOMER_ID));
                    System.out.println("CUSTOMER LAST NAME: " + "" + dbResultSet.getString (CUSTOMER_LAST_NAME));
                    System.out.println("CUSTOMER FIRST NAME: " + "" +dbResultSet.getString (CUSTOMER_FIRST_NAME) );
                    System.out.println("PAYMENT AMOUNT: " + "" + dbResultSet.getDouble(PAYMENT_AMOUNT));
                    System.out.println("PAYMENT DATE: " + "" + dbResultSet.getDate(PAYMENT_DATE));
                    System.out.println("PAYMENT TYPE: " + "" + dbResultSet.getString(PAYMENT_TYPE));
                    System.out.println("PAYMENT HISTORY ID: " + "" + dbResultSet.getString (PAYMENT_HISTORY_ID));
                    System.out.println("----------" + loopCount + "----------\n");
                    availableData = true;
                }
                if (availableData) {
                    System.out.println("\nNumber of records: " + loopCount + ".");
                } else {
                    System.out.println("No data available.");
                }

            }catch (Exception e){

            }

        }

        public static void displayPaymentHistory(int inputCustomerId) {
            PaymentHistory newPaymentHistory = new PaymentHistory();
            try {
                String getPaymentHistory = " SELECT * FROM " +PAYMENT_HISTORY+
                        " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+ " . " +LOAN_ID+
                        " = " +LOAN+ " . " + LOAN_ID + " INNER JOIN " +CUSTOMER+ " ON " +LOAN+
                        " . " +CUSTOMER_ID+ " = " +CUSTOMER+ " . " +CUSTOMER_ID+
                        " WHERE " +LOAN+ " . " +CUSTOMER_ID+ " = ? ";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistory);
                int loopCount = 0;
                Boolean availableData = false;
                dbPreparedStatement.setInt(1, inputCustomerId);
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                while (dbResultSet.next()) {
                    newPaymentHistory.setInputCustomerId(dbResultSet.getInt(CUSTOMER_ID));
                    newPaymentHistory.setCustomerLastName(dbResultSet.getString(CUSTOMER_LAST_NAME));
                    newPaymentHistory.setCustomerFirstName(dbResultSet.getString(CUSTOMER_FIRST_NAME));
                    newPaymentHistory.setPaymentAmount(dbResultSet.getDouble(PAYMENT_AMOUNT));
                    newPaymentHistory.setPaymentDate(dbResultSet.getDate(PAYMENT_DATE));
                    newPaymentHistory.setPaymentType(dbResultSet.getString(PAYMENT_TYPE));
                    newPaymentHistory.setPaymentHistoryId(dbResultSet.getShort(PAYMENT_HISTORY_ID));

                    loopCount++;
                    System.out.println("----------" + loopCount + "----------");
                    System.out.println("CUSTOMER ID: " + "" + dbResultSet.getInt(CUSTOMER_ID));
                    System.out.println("CUSTOMER LAST NAME: " + "" + dbResultSet.getString(CUSTOMER_LAST_NAME));
                    System.out.println("CUSTOMER FIRST NAME: " + "" + dbResultSet.getString(CUSTOMER_FIRST_NAME));
                    System.out.println("PAYMENT AMOUNT: " + "" + dbResultSet.getDouble(PAYMENT_AMOUNT));
                    System.out.println("PAYMENT DATE: " + "" + dbResultSet.getDate(PAYMENT_DATE));
                    System.out.println("PAYMENT TYPE: " + "" + dbResultSet.getString(PAYMENT_TYPE));
                    System.out.println("PAYMENT HISTORY ID: " + "" + dbResultSet.getString(PAYMENT_HISTORY_ID));
                    System.out.println("----------" + loopCount + "----------\n");
                    availableData = true;

                }
                if (availableData) {
                    System.out.println("\nNumber of records: " + loopCount + ".");
                } else {
                    System.out.println("No data available.");
                }
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public static void displayPaymentHistoryCustomerDatabase(int userId){
            PaymentHistory newPaymentHistory = new PaymentHistory();
            try{
                String getPaymentHistoryCustomer =  " SELECT * FROM " +PAYMENT_HISTORY+
                                                    " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+ " . " +LOAN_ID+
                                                    " = " +LOAN+ " . " +LOAN_ID+ " INNER JOIN " +CUSTOMER+
                                                    " ON " +LOAN+ " . " +CUSTOMER_ID+ " = " +CUSTOMER+
                                                    " . " +CUSTOMER_ID+ " WHERE " +LOAN+ " . " +CUSTOMER_ID+
                                                    " = " +userId+ " ";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistoryCustomer);
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                System.out.println("Customer Last Name \t Customer First Name \t Payment Amount \t Payment Date \t Payment Type");
                while(dbResultSet.next()){

                    System.out.println("CUSTOMER LAST NAME: " + "" + dbResultSet.getString(CUSTOMER_LAST_NAME));
                    System.out.println("CUSTOMER FIRST NAME: " + "" + dbResultSet.getString(CUSTOMER_FIRST_NAME));
                    System.out.println("PAYMENT AMOUNT: " + "" + dbResultSet.getDouble(PAYMENT_AMOUNT));
                    System.out.println("PAYMENT DATE: " + "" + dbResultSet.getDate(PAYMENT_DATE));
                    System.out.println("PAYMENT TYPE: " + "" + dbResultSet.getString(PAYMENT_TYPE));
                    System.out.println("PAYMENT HISTORY ID: " + "" + dbResultSet.getString(PAYMENT_HISTORY_ID));

                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }

    public static void insertIntoPaymentHistory(){
            //do stuff
    }

//public static void main(String[] args){

            //PaymentDisplayDatabase pd = new PaymentDisplayDatabase();
            //pd.displayPaymentHistoryCustomerDatabase();

}

   // public static void checkInputID( ) throws SQLException {
   //    String checkIfIdExists = "SELECT * FROM PAYMENT_HISTORY WHERE" +inputLoanId+"=?";
   //    PreparedStatement checkInputId = databaseConnection.prepareStatement(checkIfIdExists);
//
//  }

