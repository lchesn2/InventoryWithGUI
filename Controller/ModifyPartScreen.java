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
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartScreen extends Inventory implements Initializable {


    public TextField modifyPartScreenIdTextField;
    public TextField modifyPartScreenNameTextField;
    public TextField modifyPartScreenInvoiceTextField;
    public TextField modifyPartScreenPriceTextField;
    public TextField modifyPartScreenMinTextField;
    public TextField modifyPartScreenMaxTextField;
    public RadioButton modifyPartInHouseRadioButton;
    public RadioButton modifyPartOutsourcedRadioButton;
    public Button modifyPartSaveButton;
    public TextField id_or_name;
    public ToggleGroup radio_button;
    public Button modifyPartCancelButton;
    public int gotTheId;
    public Label changingLabel;
    boolean wasInHouse;

    public void checkClass(Part part){
        boolean isInHouse;
        isInHouse = (modifyPartInHouseRadioButton.isSelected());
        int id = Integer.parseInt(modifyPartScreenIdTextField.getText());
        String name = modifyPartScreenNameTextField.getText();
        double price = Double.parseDouble(modifyPartScreenPriceTextField.getText());
        int stock = Integer.parseInt(modifyPartScreenInvoiceTextField.getText());
        int min = Integer.parseInt(modifyPartScreenMinTextField.getText());
        int max = Integer.parseInt(modifyPartScreenMaxTextField.getText());

        if(isInHouse && wasInHouse) {//in house selected and previously in house, then update data
            modData(lookupPart(gotTheId));

        }
        if(!isInHouse && wasInHouse){//outsourced and previously in house, then create new outsourced
            //changingLabel.setText("Machine ID");
            String coName = id_or_name.getText();
            deletePart(gotTheId);
            Outsourced newer =new Outsourced(id, name, price, stock, min, max, coName);
            Inventory.addPart(newer);
        }
        if(isInHouse && !wasInHouse){//in house selected, previously outsourced
            //changingLabel.setText("Co. Name");
            int machineId = Integer.parseInt(id_or_name.getText());
            deletePart(gotTheId);
            InHouse newer =new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(newer);
        }
        if(!isInHouse && !wasInHouse)
            modData(lookupPart(gotTheId));

    }
    public void modData(Part part){
        part.setPartName(modifyPartScreenNameTextField.getText());
        part.setPartStock(Integer.parseInt(modifyPartScreenInvoiceTextField.getText()));
        part.setPartMin(Integer.parseInt(modifyPartScreenMinTextField.getText()));
        part.setPartMax(Integer.parseInt(modifyPartScreenMaxTextField.getText()));
        part.setPartPrice(Double.parseDouble(modifyPartScreenPriceTextField.getText()));

        if(part instanceof InHouse){
            changingLabel.setText("Machine ID");
            ((InHouse)part).setMachineId(Integer.parseInt(id_or_name.getText()));
        }
        if(part instanceof Outsourced){
            changingLabel.setText("Co.Name");
            ((Outsourced)part).setCompanyName(id_or_name.getText());
        }

    }
    public void sendPart(Part part){


        modifyPartScreenIdTextField.setText(String.valueOf(part.getPartId()));
        modifyPartScreenNameTextField.setText(part.getPartName());
        modifyPartScreenInvoiceTextField.setText(String.valueOf(part.getPartStock()));
        modifyPartScreenMinTextField.setText(String.valueOf(part.getPartMin()));
        modifyPartScreenMaxTextField.setText(String.valueOf(part.getPartMax()));
        modifyPartScreenPriceTextField.setText(String.valueOf(part.getPartPrice()));
        gotTheId =part.getPartId();

        if(part instanceof InHouse){
            modifyPartInHouseRadioButton.fire();
            changingLabel.setText("Machine ID");
            wasInHouse = true;
            id_or_name.setText(String.valueOf(((InHouse)part).getMachineId()));
            System.out.println("previously in house");
        }
        if(part instanceof Outsourced) {
            modifyPartOutsourcedRadioButton.fire();
            changingLabel.setText("Co. Name");
            wasInHouse = false;
            id_or_name.setText(((Outsourced)part).getCompanyName());
            System.out.println("previously outsourced");
        }
    }


    public void text_in_field(ActionEvent actionEvent) {
    }

    public void Name_entered(ActionEvent actionEvent) {
    }

    public void Invoice_Entered(ActionEvent actionEvent) {
    }

    public void Price_Entered(ActionEvent actionEvent) {
    }

    public void Min_Entered(ActionEvent actionEvent) {
    }

    public void Max_Entered(ActionEvent actionEvent) {
    }

    public void InHouse_Selected(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");

    }

    public void Outsourced_Selected(ActionEvent actionEvent) {
        changingLabel.setText("Co. Name");
    }

    public void Save_Clicked(ActionEvent actionEvent) throws NumberFormatException, IOException {

        try{checkClass(lookupPart(gotTheId));

            setStage(actionEvent, "/view/MainScreen.fxml");

        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialogue");
            alert.setContentText("Please enter a valid value for each text field");
            alert.showAndWait();

            System.out.println("Exception" + e.getMessage());
        }
    }

    public void Cancel_Clicked(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            setStage(actionEvent, "/view/MainScreen.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void id_or_name_textF(ActionEvent actionEvent) {
    }
}
