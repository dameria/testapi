package com.hcitest.testapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProducts {
    @SerializedName("section")
    String section;
    @SerializedName("items")
    List<Product> productList;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
