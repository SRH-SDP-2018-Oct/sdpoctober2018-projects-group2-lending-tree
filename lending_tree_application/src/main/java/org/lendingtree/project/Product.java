package org.lendingtree.project;

public class Product {

    private int productId;
    private int productTypeId;
    private String productDescription;
    private int representativeId;
    private float productAmount;
    private float productInterestRate;
    private int productNumberOfPayments;
    private String productAvailabilityFrom;
    private String productAvailabilityTo;
    private boolean productActiveStatus;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    public float getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(float productAmount) {
        this.productAmount = productAmount;
    }

    public float getProductInterestRate() {
        return productInterestRate;
    }

    public void setProductInterestRate(float productInterestRate) {
        this.productInterestRate = productInterestRate;
    }

    public int getProductNumberOfPayments() {
        return productNumberOfPayments;
    }

    public void setProductNumberOfPayments(int productNumberOfPayments) {
        this.productNumberOfPayments = productNumberOfPayments;
    }

    public String getProductAvailabilityFrom() {
        return productAvailabilityFrom;
    }

    public void setProductAvailabilityFrom(String productAvailabilityFrom) {
        this.productAvailabilityFrom = productAvailabilityFrom;
    }

    public String getProductAvailabilityTo() {
        return productAvailabilityTo;
    }

    public void setProductAvailabilityTo(String productAvailabilityTo) {
        this.productAvailabilityTo = productAvailabilityTo;
    }

    public boolean isProductActiveStatus() {
        return productActiveStatus;
    }

    public void setProductActiveStatus(boolean productActiveStatus) {
        this.productActiveStatus = productActiveStatus;
    }
}
