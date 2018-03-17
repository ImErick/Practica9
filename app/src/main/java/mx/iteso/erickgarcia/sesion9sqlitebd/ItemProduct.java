package mx.iteso.erickgarcia.sesion9sqlitebd;

/**
 * Created by erickgarcia on 17/03/18
 */

public class ItemProduct {
    String productName, productCategory;
    float productPrice;

    public ItemProduct(){

    }

    public ItemProduct(String productName, String productCategory, float productPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
