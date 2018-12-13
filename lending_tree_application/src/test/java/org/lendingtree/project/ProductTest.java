package org.lendingtree.project;

import org.junit.Test;

import java.util.ArrayList;

public class ProductTest {

    @Test
    public void createProductTest() {
        Product newProduct = new Product();
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

            ProductDatabase.createProduct(newProduct);

            System.out.println("The product was added successfully.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void updateProductTest() {
        Product product = new Product();
        int productId = 4;
        String productDescription = "Potato";

        try {

            product.setProductId(productId);
            product.setProductDescription(productDescription);

            ProductDatabase.updateProduct(product);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void disableProductTest() {
        Product product = new Product();
        int productId = 3;
        boolean activeStatus = false;

        try {

            product.setProductId(productId);
            product.setProductActiveStatus(activeStatus);

            ProductDatabase.disableProduct(product);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getProductFromRepresentativeTest() {
        int representativeId = 2;
        boolean allProducts = true;

        try {
            ProductDatabase.getProductFromRepresentative(representativeId, allProducts);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void getAllActiveProductsTest() {
        Product activeProduct = new Product();
        boolean activeStatus = true;
        boolean filterOn = false;

        try {
            activeProduct.setProductActiveStatus(activeStatus);
            ProductDatabase.getAllActiveProducts(activeProduct, filterOn);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllActiveProductsWithParametersTest() {
        Product searchProduct = new Product();
        boolean activeStatus = true;
        boolean filtersOn = true;
        String productDescription = "Test";
        Double productAmount = 1.23;
        Double productInterestRate = 0.1;
        int productNumberOfPayments = 24;

        try {
            searchProduct.setProductActiveStatus(activeStatus);
            searchProduct.setProductDescription(productDescription);
            searchProduct.setProductAmount(productAmount);
            searchProduct.setProductInterestRate(productInterestRate);
            searchProduct.setProductNumberOfPayments(productNumberOfPayments);
            ProductDatabase.getAllActiveProducts(searchProduct, filtersOn);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getProductDetailsTest() {
        ArrayList<Integer> productIds = new ArrayList<>();
        productIds.add(2);
        productIds.add(3);
        productIds.add(4);
        boolean activeStatus = true;

        try {
            ProductDatabase.getProductDetails(productIds, activeStatus);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllProductTypeDescriptionTest() {
        try {
            ProductDatabase.getAllProductTypeDescription();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
