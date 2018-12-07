package org.lendingtree.project;

public class Loan {

    public int loanId;
    public int customerId;
    public int productId;
    public int loanStatusId;
    public String loanDateApplied;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(int loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getLoanDateApplied() {
        return loanDateApplied;
    }

    public void setLoanDateApplied(String loanDateApplied) {
        this.loanDateApplied = loanDateApplied;
    }

}
