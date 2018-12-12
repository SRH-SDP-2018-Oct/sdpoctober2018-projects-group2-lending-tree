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
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT = "product";
    private static final String REPRESENTATIVE = "representative";
    private static final String REPRESENTATIVE_ID = "representative_id";

    protected static Connection databaseConnection = ConnectMSSQLServer.getConnection();

        public static void displayPaymentHistoryRepresentative(int inputRepresentativeID){

            PaymentHistory newPaymentHistory = new PaymentHistory();
            try {

                String getPaymentHistoryForAll = " SELECT * FROM " +PAYMENT_HISTORY+
                                                 " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+
                                                 " . " +LOAN_ID+ " = " +LOAN+ " . " +LOAN_ID+
                                                 " INNER JOIN " +PRODUCT+ " ON " +LOAN+ " . "
                                                 +PRODUCT_ID+ " = " +PRODUCT+ " . " +PRODUCT_ID+ " INNER JOIN "
                                                 +REPRESENTATIVE+ " ON " +PRODUCT+ " . " +REPRESENTATIVE_ID+ " = "
                                                 +REPRESENTATIVE+ " . " +REPRESENTATIVE_ID+ " INNER JOIN " +CUSTOMER+ " ON "
                                                 +LOAN+ " . " +CUSTOMER_ID+ " = " +CUSTOMER+ " . " +CUSTOMER_ID+
                                                 " WHERE " +PRODUCT+
                                                 " . " +REPRESENTATIVE_ID+ " = ? " ;
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistoryForAll);
                dbPreparedStatement.setInt(1, inputRepresentativeID);
                Boolean availableData = false;
                int loopCount =0;
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                System.out.println("You have the following customers :");
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
                e.printStackTrace();
            }

        }

        public static void displayPaymentHistoryRepresentativeSort(int userId,int inputCustomerId) {
            PaymentHistory newPaymentHistory = new PaymentHistory();
            try {
                String getPaymentHistory = " SELECT * FROM " +PAYMENT_HISTORY+
                        " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+ " . " +LOAN_ID+
                        " = " +LOAN+ " . " + LOAN_ID + " INNER JOIN " +CUSTOMER+ " ON " +LOAN+
                        " . " +CUSTOMER_ID+ " = " +CUSTOMER+ " . " +CUSTOMER_ID+
                        " INNER JOIN " + PRODUCT + " ON " + LOAN + " . " + PRODUCT_ID + " = "
                        + PRODUCT + " . " + PRODUCT_ID +
                        " WHERE " +LOAN+ " . " +CUSTOMER_ID+ " = ? AND " +REPRESENTATIVE_ID + " = ?" ;
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistory);
                int loopCount = 0;
                Boolean availableData = false;
                dbPreparedStatement.setInt(1, inputCustomerId);
                dbPreparedStatement.setInt(2,userId);
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

        public static void displayPaymentHistoryCustomer(int userId){
            int loopCount = 0;
            Boolean availableData = false;
            try{
                String getPaymentHistoryCustomer =  " SELECT * FROM " +PAYMENT_HISTORY+
                                                    " INNER JOIN " +LOAN+ " ON " +PAYMENT_HISTORY+ " . " +LOAN_ID+
                                                    " = " +LOAN+ " . " +LOAN_ID+ " INNER JOIN " +CUSTOMER+
                                                    " ON " +LOAN+ " . " +CUSTOMER_ID+ " = " +CUSTOMER+
                                                    " . " +CUSTOMER_ID+ " WHERE " +LOAN+ " . " +CUSTOMER_ID+
                                                    " = " +userId+ " ";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(getPaymentHistoryCustomer);
                ResultSet dbResultSet = dbPreparedStatement.executeQuery();
                while(dbResultSet.next()){
                    loopCount++;
                    System.out.println("----------" + loopCount + "----------");
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
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }

        public static void insertIntoPaymentHistory(PaymentHistory newPaymentHistory){
            try {
                String insertQuery = " INSERT INTO " + PAYMENT_HISTORY +
                        " ( " + LOAN_ID + " , " + PAYMENT_AMOUNT +
                        " , " + PAYMENT_DATE + " , " + PAYMENT_TYPE + " ) "+
                        " VALUES( ?, ?, ?, ? ) ";
                PreparedStatement dbPreparedStatement = databaseConnection.prepareStatement(insertQuery);

                dbPreparedStatement.setInt(1, newPaymentHistory.getLoanID());
                dbPreparedStatement.setDouble(2,newPaymentHistory.getPaymentAmount());
                dbPreparedStatement.setDate(3, (Date) newPaymentHistory.getPaymentDate());
                dbPreparedStatement.setString(4, newPaymentHistory.getPaymentType());

                dbPreparedStatement.executeUpdate();

            }catch (Exception exception){
                exception.printStackTrace();
            }

    }

}

