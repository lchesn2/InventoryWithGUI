package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;

    public Product(int id, String name, double price, int stock, int min, int max){

        this.productId=id;
        this.productName=name;
        this.productPrice=price;
        this.productStock=stock;
        this.productMin=min;
        this.productMax=max;
    }


    public int getProductId(){return productId;}

    public void setProductId(int id){this.productId=id;}

    public String getProductName(){return productName;}

    public void setProductName(String name){this.productName = name;}

    public double getProductPrice(){return productPrice;}

    public void setProductPrice(double price){this.productPrice=price;}

    public int getProductStock(){return productStock;}

    public void setProductStock(int stock){this.productStock=stock;}

    public int getProductMin(){return productMin;}

    public void setProductMin(int min){this.productMin=min;}

    public int getProductMax(){return productMax;}

    public void setProductMax(int max){this.productMax=max;}

    public void addAssociatedPart(Part part){ associatedParts.add(part);   }

    public boolean deleteAssociatedParts(int id){
        for(Part p : getAllAssociatedParts()) {
            if(p.getPartId() == id) {
                getAllAssociatedParts().remove(p);
                return true;
            }
        }
        return false;
    }
    public ObservableList<Part> getAllAssociatedParts(){return associatedParts;};

}
