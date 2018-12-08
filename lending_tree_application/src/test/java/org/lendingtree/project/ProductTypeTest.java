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
}
