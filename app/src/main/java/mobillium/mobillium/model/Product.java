package mobillium.mobillium.model;

import java.util.ArrayList;

import mobillium.mobillium.R;

/**
 * Created by tchzafer on 21/03/2018.
 */

public class Product {

    private String productName;
    private String productDescription;
    private int imageID;

    public Product() {
    }

    public Product(int imageID, String productName, String productDescription) {
        this.imageID = imageID;
        this.productName = productName;
        this.productDescription = productDescription;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public static ArrayList<Product> getData() {
        ArrayList<Product> productList = new ArrayList<Product>();
        int productImages[] = {R.drawable.canta, R.drawable.ayakkabi, R.drawable.elbise1,R.drawable.elbise2};
        String[] productNames = {"Sırtı Bağlama Çanta", "Boncuk Detaylı Ayakkabı", "Sırtı Bağlama Çanta","Boncuk Detaylı Ayakkabı"};
        String[] productDescription = {"53,00TL","48,00TL","53,00TL","48,00TL"};
        for (int i = 0; i < productImages.length; i++) {
            Product temp = new Product();
            temp.setImageID(productImages[i]);
            temp.setProductName(productNames[i]);
            temp.setProductDescription(productDescription[i]);

            productList.add(temp);

        }


        return productList;


    }
}