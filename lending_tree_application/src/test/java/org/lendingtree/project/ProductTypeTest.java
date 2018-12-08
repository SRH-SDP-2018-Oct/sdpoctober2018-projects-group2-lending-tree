package org.lendingtree.project;

import org.junit.Test;

public class ProductTypeTest {

    @Test
    public void getProductTypeDescriptionTest() {
        ProductType newProductType = new ProductType();
        int productTypeId = 1;

        try {
            newProductType.setProductTypeId(productTypeId);
            System.out.println(ProductTypeDatabase.getProductTypeDescription(newProductType.getProductTypeId()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllProductTypeDescriptionTest() {
        try {
            ProductTypeDatabase.getAllProductTypeDescription();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateProductTypeDescriptionTest() {
        ProductType newProductType = new ProductType();
        int productTypeId = 1;
        String productTypeDescription = "Changed from the test";

        try {
            newProductType.setProductTypeId(productTypeId);
            newProductType.setProductTypeDescription(productTypeDescription);
            ProductTypeDatabase.updateProductTypeDescription(newProductType);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void createProductTypeTest() {
        ProductType newProductType = new ProductType();
        String productTypeDescription = "New product type added from test";

        try {
            newProductType.setProductTypeDescription(productTypeDescription);
            ProductTypeDatabase.createProductType(newProductType.getProductTypeDescription());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
