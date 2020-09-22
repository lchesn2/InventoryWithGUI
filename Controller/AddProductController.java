package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddProductController extends Inventory implements Initializable {
    public TextField addProdIdTextField;
    public TextField addProdNameTextField;
    public TextField addProdInvoiceTextField;
    public TextField addProdPriceTextField;
    public TextField addProdMinTextField;
    public TextField addProdMaxTextField;

    public TableColumn<Part, Integer> addProdPartIdCol;
    public TableColumn<Part, String> addProdPartNameCol;
    public TableColumn<Part, Integer> addProdPartStockCol;
    public TableColumn<Part, Double> addProdPartPriceCol;

    public TableColumn<Part, Integer> addProdProductIdCol;
    public TableColumn<Part, String> addProdProductNameCol;
    public TableColumn<Part, Integer> addProdProductStockCol;
    public TableColumn<Part, Double> addProdProductPriceCol;

    public TextField addProdSearchTextField;
    public Button addProdAddButton;
    public Button addProdSaveButton;
    public Button addProdDeleteButton;
    public Button addProdCancelButton;
    public TableView<Part> addProdPartTable;
    public TableView<Part> addProdProdTable;
    public Button searchButton;

    public StringBuilder nameIt(){
    StringBuilder name = new StringBuilder(addProdNameTextField.getText());
    int size = name.length();
    name.delete(2, size);
    return name;
    }



    public void setProdIdField(){

        int id = Inventory.getAllProducts().size() + 12;
        for(Product p: Inventory.getAllProducts()){
            if(p.getProductId()== id){
                do{
                    id ++;

                }while(p.getProductId() == id);
            }}
        System.out.println("new id is equal to " + id);
        addProdIdTextField.setText(String.valueOf(id));
    }
    ObservableList<Part> tempPartList = FXCollections.observableArrayList();

    public void add_prod_added(ActionEvent actionEvent) {

        Part selPart = (Part)addProdPartTable.getSelectionModel().getSelectedItem();
        if(selPart==null)
            return;
        tempPartList.add(selPart);
        System.out.println("selected part added to associated parts list");


    }
    public class noProductException extends Exception{
        public noProductException(){
            super();
        }
    }

    public void add_prod_saved(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(addProdIdTextField.getText());
            String name = addProdNameTextField.getText();
            int stock = Integer.parseInt(addProdInvoiceTextField.getText());
            double price = Double.parseDouble(addProdPriceTextField.getText());
            int min = Integer.parseInt(addProdMinTextField.getText());
            int max = Integer.parseInt(addProdMaxTextField.getText());


            Product newest = new Product(id, name, price, stock, min, max);
            for(Part p : tempPartList){
                newest.addAssociatedPart(p);
            }
            if(newest.getAllAssociatedParts().isEmpty())
                throw new noProductException();

            Inventory.addProduct(newest);
            setStage(actionEvent, "/view/MainScreen.fxml");
        }catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialogue");
            alert.setContentText("Please enter a valid value for each text field");
            alert.showAndWait();

            System.out.println("Exception" + e.getMessage());
        }catch(noProductException c){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Product must have at least one associated part");
            alert.showAndWait();

        }

    }

    public void add_prod_deleted(ActionEvent actionEvent) {
        Part deleted = (Part)addProdProdTable.getSelectionModel().getSelectedItem();
        if(deleted == null)
            return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
            tempPartList.remove(deleted);
    }

    public void add_prod_canceled(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            setStage(actionEvent, "/view/MainScreen.fxml");
        }

    }


    public void addProd_Id_Entered(ActionEvent actionEvent) {
    }

    public void addProd_Name_Entered(ActionEvent actionEvent) {
    }

    public void addProd_Stock_Entered(ActionEvent actionEvent) {
    }

    public void addProd_Price_Entered(ActionEvent actionEvent) {
    }

    public void addProd_Min_Entered(ActionEvent actionEvent) {
    }

    public void addProd_Max_Entered(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    addProdPartTable.setItems(Inventory.getAllParts());
    addProdProdTable.setItems(tempPartList);

    addProdProductIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
    addProdProductNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
    addProdProductStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
    addProdProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    addProdPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));//get id, populate cell
    addProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
    addProdPartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
    addProdPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));



    }

    public void searched(ActionEvent actionEvent) {
        String userSearch = addProdSearchTextField.getText();
        System.out.println(lookupPart(userSearch));
        if (!(Inventory.getFilteredParts().isEmpty()))
            Inventory.getFilteredParts().clear();
        Inventory.filteredParts = lookupPart(userSearch);
        addProdPartTable.setItems(Inventory.getFilteredParts());
        if (Inventory.filteredParts.size() == 0) {
            try {
                int id = Integer.parseInt(userSearch);
                Part p = lookupPart(id);
                if (p != null)
                    Inventory.filteredParts.add(p);
            } catch (NumberFormatException e) {
                System.out.println("Exception" + e.getMessage());
            }
            System.out.println("Showing filtered list");
        }
    }

    public void addProd_Part_Searched(ActionEvent actionEvent) {
    }
}
