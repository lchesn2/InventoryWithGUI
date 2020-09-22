package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddPartScreen extends Inventory implements Initializable {
    public TextField addPartScreenIdTextField;
    public TextField addPartScreenNameTextField;
    public TextField addPartScreenInvoiceTextField;
    public TextField addPartScreenPriceTextField;
    public TextField addPartScreenMinTextField;
    public TextField addPartScreenMaxTextField;
    public RadioButton addPartInHouseRadioButton;
    public RadioButton addPartOutsourcedRadioButton;
    public Button addPartSaveButton;
    public Button addPartCancelButton;
    public TextField whatTypeIsIt;
    public Label changingLabel;

    public void setIdField(){
        int id = Inventory.getAllParts().size() + 100;
        for(Part p: Inventory.getAllParts()){
            if(p.getPartId()== id){
                do{
                id ++;

            }while(p.getPartId() == id);
        }}
        System.out.println("new id is equal to " + id);
        addPartScreenIdTextField.setText(String.valueOf(id));
    }
    public void typeEntered(ActionEvent actionEvent) {

    }

    public void addPart_Name_entered(ActionEvent actionEvent) {


    }

    public void addPart_Invoice_Entered(ActionEvent actionEvent) {
    }

    public void addPart_Price_Entered(ActionEvent actionEvent) {
    }

    public void addPart_Min_Entered(ActionEvent actionEvent) {
    }

    public void addPart_Max_Entered(ActionEvent actionEvent) {
    }

    public void addPart_InHouse_Selected(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");

    }

    public void addPart_Outsourced_Selected(ActionEvent actionEvent) {
        changingLabel.setText("Co. Name");

    }


    public void addPart_Save_Clicked(ActionEvent actionEvent) throws IOException{
            try{
                int id = Integer.parseInt(addPartScreenIdTextField.getText());
                String name = addPartScreenNameTextField.getText();
                int stock = Integer.parseInt(addPartScreenInvoiceTextField.getText());
                double price = Double.parseDouble(addPartScreenPriceTextField.getText());
                int min = Integer.parseInt(addPartScreenMinTextField.getText());
                int max = Integer.parseInt(addPartScreenMaxTextField.getText());
                boolean inHouse;

                inHouse = (addPartInHouseRadioButton.isSelected());

                if (inHouse) {

                    int machineId = Integer.parseInt(whatTypeIsIt.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                } else {

                    String coName = whatTypeIsIt.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, coName));
                }

                setStage(actionEvent, "/view/MainScreen.fxml");

            }catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a valid value for each text field");
                alert.showAndWait();

               System.out.println("Exception" + e.getMessage());
            }




    }

    public void addPart_Cancel_Clicked(ActionEvent actionEvent) throws IOException {//ADD DISCARD CHANGES PROMPT
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
        setStage(actionEvent, "/view/MainScreen.fxml");
        }
    }

    public void addPart_id_entered(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    addPartInHouseRadioButton.fire();



    }



}
