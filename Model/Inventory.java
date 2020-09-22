package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

public class Inventory {
    public static ObservableList<Part> allParts= FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts= FXCollections.observableArrayList();
    public static ObservableList<Product> filteredProducts= FXCollections.observableArrayList();
    public static ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    public static void addPart(Part part) { allParts.add(part);}
    public static void addProduct(Product product) {allProducts.add(product);}

    public static Part lookupPart(int id){
        for(Part part: Inventory.getAllParts()){
            if(part.getPartId() == id){
                return part;
            }
        }
        return null;
    }
    public static Product lookupProduct(int id) {
        for (Product p : Inventory.getAllProducts()) {
            if(p.getProductId()==id)
                return p;
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String name){

        for(Part part : Inventory.getAllParts()) {
            if (part.getPartName().contains(name)) {
                Inventory.getFilteredParts().add(part);
            }
        }
        return Inventory.getFilteredParts();
    }
    public static ObservableList<Product> lookupProduct(String name){

        for(Product p: Inventory.getAllProducts()) {
            if (p.getProductName().contains(name))
                Inventory.getFilteredProducts().add(p);
        }
        return Inventory.getFilteredProducts();
    }
    public static boolean updatePart(int id, Part part){
        int index = -1;
        for(Part p: Inventory.getAllParts()){
            if(p.getPartId()== id){
                Inventory.getAllParts().set(index, p);
                return true;
            }
        }

        return false;
    }

    public static boolean deletePart(int id){
        for(Part part : Inventory.getAllParts()) {
            if(part.getPartId() == id)
                return Inventory.getAllParts().remove(part);
        }
        return false;
    }
    public static boolean deleteProduct(int id){
        for(Product p : Inventory.getAllProducts()){
            if(p.getProductId() == id)
                return Inventory.getAllProducts().remove(p);
        }
        return false;
    }

    public static ObservableList<Product> getFilteredProducts(){return filteredProducts;}
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    public static ObservableList<Part> getFilteredParts(){return filteredParts;}

    public int gotProductId;



    public void setStage(ActionEvent actionEvent, String location) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }





}
