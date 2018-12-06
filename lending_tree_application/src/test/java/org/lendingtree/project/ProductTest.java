package org.lendingtree.project;

import org.junit.Test;

public class ProductTest {

    @Test
    public void createProductTest() {
        Product newProduct = new Product();
        int productId = 1;
        int productTypeId = 1;
        String productDescription = "Test";
        int representativeId = 2;
        Double productAmount = 1.23;
        Double productInterestRate = 0.1;
        int productNumberOfPayments = 24;
        String productAvailabilityFrom = "05/20/2018";
        String productAvailabilityTo = "05/20/2020";
        boolean productActiveStatus = true;

        try {

            newProduct.setProductTypeId(productTypeId);
            newProduct.setProductDescription(productDescription);
            newProduct.setRepresentativeId(representativeId);
            newProduct.setProductAmount(productAmount);
            newProduct.setProductInterestRate(productInterestRate);
            newProduct.setProductNumberOfPayments(productNumberOfPayments);
            newProduct.setProductAvailabilityFrom(productAvailabilityFrom);
            newProduct.setProductAvailabilityTo(productAvailabilityTo);
            newProduct.setProductActiveStatus(productActiveStatus);

            ProductDatabase.insertProduct(newProduct);

            System.out.println("The product was added successfully.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
