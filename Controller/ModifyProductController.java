package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

public class ModifyProductController extends Inventory implements Initializable {
    public TextField modProdIdTextField;
    public TextField modProdNameTextField;
    public TextField modProdStockTextField;
    public TextField modProdPriceTextField;
    public TextField modProdMinTextField;
    public TextField modProdMaxTextField;
    public TableColumn modProdPartIdCol;
    public TableColumn modProdPartNameCol;
    public TableColumn modProdPartStockCol;
    public TableColumn modProdPartPriceCol;
    public TableColumn modProdProdIdCol;
    public TableColumn modProdProdNameCol;
    public TableColumn modProdProdStockCol;
    public TableColumn modProdProdPriceCol;
    public TextField modProdSearchTextField;
    public Button modProdAddButton;
    public Button modProdSaveButton;
    public Button modProdDeleteButton;
    public Button modProdCancelButton;
    public Button Search;
    public TableView productTable;
    public TableView partsTable;
    private ObservableList<Part> bottomTable= FXCollections.observableArrayList();
    public class noProductException extends Exception {
        public noProductException() {
            super();
        }
    }

    public void modProd(Product p){
        p.setProductName(modProdNameTextField.getText());
        p.setProductStock(Integer.parseInt(modProdStockTextField.getText()));
        p.setProductMin(Integer.parseInt(modProdMinTextField.getText()));
        p.setProductMax(Integer.parseInt(modProdMaxTextField.getText()));
        p.setProductPrice(Double.parseDouble(modProdPriceTextField.getText()));
        p.getAllAssociatedParts().clear();
        for(Part pt : bottomTable){
            p.addAssociatedPart(pt);
        }
    }
    public void sendInternalParts(Product product){
        gotProductId = product.getProductId();

        modProdIdTextField.setText(String.valueOf(product.getProductId()));
        modProdNameTextField.setText(product.getProductName());
        modProdStockTextField.setText(String.valueOf(product.getProductStock()));
        modProdMinTextField.setText(String.valueOf(product.getProductMin()));
        modProdMaxTextField.setText(String.valueOf(product.getProductMax()));
        modProdPriceTextField.setText(String.valueOf(product.getProductPrice()));//8api give String to format
        partsTable.setItems(Inventory.getAllParts());
        bottomTable.addAll(product.getAllAssociatedParts());
        productTable.setItems(bottomTable);


    }

    public void Searched(ActionEvent actionEvent) {
        String userSearch = modProdSearchTextField.getText();
        if(!(Inventory.getFilteredParts().isEmpty()))
            Inventory.getFilteredParts().clear();
        Inventory.filteredParts= lookupPart(userSearch);
        if(Inventory.filteredProducts.size()==0) {
            try {
                int id = Integer.parseInt(userSearch);
                Product p = lookupProduct(id);
                if (p != null)
                    Inventory.filteredProducts.add(p);
            } catch (NumberFormatException e) {
                System.out.println("Exception" + e.getMessage());
            }
        }
        partsTable.setItems(Inventory.getFilteredParts());

    }


    public void modProd_id_entered(ActionEvent actionEvent) {
    }


    public void modProd_partAdded(ActionEvent actionEvent) {

        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null)
            return;
        bottomTable.add(selectedPart);
        //searchProdId(gotProductId).addAssociatedPart(selectedPart);

    }

    public void modProd_Prod_Saved(ActionEvent actionEvent) throws NumberFormatException {
        try{
            if(bottomTable.isEmpty())
                throw new noProductException();
            modProd(lookupProduct(gotProductId));
            setStage(actionEvent, "/view/MainScreen.fxml");
        }
        catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }catch(noProductException ep){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Product must have at least one associated part");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modProd_Prod_Deleted(ActionEvent actionEvent) throws IOException {
        Part selectedPart = (Part)productTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null)
            return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            bottomTable.remove(selectedPart);
        }

        //searchProdId(gotProductId).deleteAssociatedParts(selectedPart.getPartId());
    }

    public void modProd_Canceled(ActionEvent actionEvent)throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            setStage(actionEvent, "/view/MainScreen.fxml");
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        modProdPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modProdPartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modProdPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modProdProdIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));//get id, populate cell
        modProdProdNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modProdProdStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modProdProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    }


    public void modProd_searched(ActionEvent actionEvent) {
    }
}
