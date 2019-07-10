package com.hcitest.testapi.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_name")
    String product_name;
    @SerializedName("product_image")
    String product_image;
    @SerializedName("link")
    String link;

    public Product(String product_name, String product_image, String link) {
        this.product_name = product_name;
        this.product_image = product_image;
        this.link = link;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
