package org.lendingtree.project;

import org.junit.Test;

public class ProductTypeTest {

    @Test
    public void getProductTypeDescriptionTest() {
        ProductType newProductType = new ProductType();
        newProductType.setProductTypeId(1);

        try {
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
        newProductType.setProductTypeId(1);
        newProductType.setProductTypeDescription("Changed from the test");

        try {
            ProductTypeDatabase.updateProductTypeDescription(newProductType.getProductTypeId(), newProductType.getProductTypeDescription());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void createProductTypeTest() {
        ProductType newProductType = new ProductType();
        newProductType.setProductTypeDescription("New product type added from test");

        try {
            ProductTypeDatabase.createProductType(newProductType.getProductTypeDescription());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
