package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainControler extends Inventory implements Initializable {
    public TableView<Part> partsTableView;
    public TableColumn<Part, Integer> partsIdCol;
    public TableColumn<Part, String> partsNameCol;
    public TableColumn<Part, Integer> partsInventoryCol;
    public TableColumn<Part, String> partsPriceCol;//changed to string
    public TableView<Product> productTableView;
    public TableColumn<Product, Integer> productIdCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productInventoryCol;
    public TableColumn<Product, Double> productPriceCol;
    public TextField partsSearchTextField;
    public Button partsSearchButton;
    public Button inventoryMainExit;
    public TextField productSearchTextField;
    public Button productSearchButton;
    public Button partsDeleteButton;
    public Button partsModifyButton;
    public Button partsAddButton;
    public Button productAddButton;
    public Button productModifyButton;
    public Button productDeleteButton;
    Stage stage;
    Parent scene;


    public void Part_Searched(ActionEvent actionEvent) throws IOException {
        String name = partsSearchTextField.getText();
        if(!(Inventory.getFilteredParts().isEmpty())) {
            Inventory.getFilteredParts().clear();
            System.out.println("full list cleared");}

        Inventory.filteredParts = lookupPart(name);
        System.out.println("Found part");
        if(Inventory.filteredParts.size()==0){
            try{
                int id = Integer.parseInt(name);
                Part p = lookupPart(id);
                System.out.println("integer found");
                if(p != null)
                    Inventory.filteredParts.add(p);
            }
            catch(NumberFormatException e){
                System.out.println("Exception caught");
            }
        }
        partsTableView.setItems(Inventory.filteredParts);
        System.out.println("Showing filtered list");

    }
    public void Product_Searched(ActionEvent actionEvent) throws IOException{
        String getUserSearch = productSearchTextField.getText();
        if(!(Inventory.getFilteredProducts().isEmpty()))
            Inventory.getFilteredProducts().clear();
        Inventory.filteredProducts = lookupProduct(getUserSearch);
        if(Inventory.filteredProducts.size()==0){
            try{
                int id = Integer.parseInt(getUserSearch);
                Product p = lookupProduct(id);
                if(p != null)
                    Inventory.filteredProducts.add(p);
            }catch(NumberFormatException e){
                System.out.println("Exception" + e.getMessage());
            }
        }
        productTableView.setItems(Inventory.getFilteredProducts());

    }


    public void Program_Exited(ActionEvent actionEvent)
    { Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        System.exit(0);
    }


    public void Part_Deleted(ActionEvent actionEvent) {
        Part delPart = partsTableView.getSelectionModel().getSelectedItem();
        if(delPart == null)
            return;
        { Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                int id =delPart.getPartId();
                if(deletePart(id))
                    System.out.println("deleted");}
        }
    }

    public void Part_Modified(ActionEvent actionEvent) throws IOException {
        if(partsTableView.getSelectionModel().getSelectedItem() == null)
            return;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyPartScreen.fxml"));
        loader.load();
        ModifyPartScreen modController = loader.getController();
        modController.sendPart(partsTableView.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
        System.out.println("new scene all set");

    }

    public void Part_Added(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddPartScreen.fxml"));
        loader.load();
        AddPartScreen addController = loader.getController();
        addController.setIdField();

        //setStage(actionEvent, "/view/AddPartScreen.fxml");
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void Product_Added(ActionEvent actionEvent) throws IOException {
        //setStage(actionEvent, "/view/AddProductScreen.fxml");
        FXMLLoader loader = new FXMLLoader();//use to call setLocation() create object
        loader.setLocation(getClass().getResource("/View/AddProductScreen.fxml"));
        loader.load();
        AddProductController prodController = loader.getController();
        prodController.setProdIdField();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
        System.out.println("new scene all set");

    }

    public void Product_Modified(ActionEvent actionEvent) throws IOException {
        if(productTableView.getSelectionModel().getSelectedItem() == null)
            return;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProductScreen.fxml"));
        loader.load();
        ModifyProductController modController = loader.getController();
        modController.sendInternalParts(productTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
        System.out.println("new scene all set");


    }

    public void Product_Deleted(ActionEvent actionEvent) {
        Product deleteProd = productTableView.getSelectionModel().getSelectedItem();
        if(deleteProd == null)
            return;

        { Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                int id =deleteProd.getProductId();
                if(deleteProduct(id))
                    System.out.println("deleted");}
        }

    }
    public void mainScreenSearchPartTxt(ActionEvent actionEvent) {
    }

    public void mainScreenSearchProductTxt(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    partsTableView.setItems(Inventory.getAllParts());

    productTableView.setItems(Inventory.getAllProducts());

    productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
    productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
    productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

    partsIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
    partsNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
    partsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
    partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
}
}
