package com.springbootaws.springawslamda.pojo;

import lombok.ToString;

@ToString
public class ProductInfo {
    private String productId;
    private String productName;
    private String productDesc;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, String productDesc) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
