package com.turtlediary.www.beans;

/**
 * Created by pratibha on 4/12/17.
 */

public class PlayStoreProductBean {
    String productId;
    String productAmountInMacros;
    String productPrice;
    String currencyCode;
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductAmountInMacros() {
        return productAmountInMacros;
    }

    public void setProductAmountInMacros(String productAmountInMacros) {
        this.productAmountInMacros = productAmountInMacros;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
