package org.lendingtree.project;

import org.junit.Test;

public class ProductTypeTest {

    @Test
    public void printProductTypeTest() {
        int productTypeId = 1;

        try {
            System.out.println(ProductTypeDatabase.getProductTypeDescription(productTypeId));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changeProductTypeDescriptionTest() {
        int productTypeId = 1;
        String newProductTypeDescription = "Changed from the test";

        try {
            ProductTypeDatabase.changeProductTypeDescription(productTypeId, newProductTypeDescription);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
