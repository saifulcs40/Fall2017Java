/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinfo;

/**
 *
 * @author Saiful Islam
 */
class Product {
    private int productId;
    private String productName;
    private String brandName;
    private double unitPrice;

    public Product() {
    }

    public Product(int productId, String productName, String brandName, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.brandName = brandName;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", brandName=" + brandName + ", unitPrice=" + unitPrice + '}';
    }
    
    
}
