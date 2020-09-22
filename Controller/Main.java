package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        primaryStage.setTitle("Seamus CO.");
        primaryStage.setScene(new Scene(root, 1075, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {//number format is abstract class decimal format create with string

        InHouse part1 = new InHouse(100, "Long Range Plus", 74_490.00, 200, 1, 1000, 8889);
        InHouse part2 = new InHouse(101, "Performance", 94_490.00, 100, 1, 1000, 8890);
        Outsourced part3 = new Outsourced(102, "Onyx Black Wheels", 5500.00, 90, 1, 1000, "Solar Friction");
        Outsourced part4 = new Outsourced(103, "Sonic Carbon Twin Turbine Wheels", 4500.00, 33, 1, 1000, "Solar Friction");
        InHouse part5 = new InHouse(104, "Midnight Silver Metallic", 1500.00, 599, 1, 1000, 577);
        InHouse part6 = new InHouse(105, "Full Self Driving Capability", 7000.00, 55, 1, 1000, 9031);
        InHouse part7 = new InHouse(106, "Red Multi-Coat", 2500.00, 99, 1, 1000, 281);
        Outsourced part8 = new Outsourced(107, "Six Seat Interior", 6500.00, 55, 1, 1000, "Newton's Auto Interior");
        Outsourced part9 = new Outsourced(108, "Sport Wheels", 1500.00, 1300, 1, 1000, "Wheels By Newton");
        InHouse part10 = new InHouse(109, "Solid Black", 1500.00, 99, 1, 1000, 576);





        Product prod1 = new Product(201, "Model S", 78_490, 37, 1, 1000);
        prod1.addAssociatedPart(part1);
        prod1.addAssociatedPart(part9);
        prod1.addAssociatedPart(part7);
        Product prod2 = new Product(202, "Model X", 107_990, 29, 1, 1000);
        prod2.addAssociatedPart(part2);
        prod2.addAssociatedPart(part3);
        prod2.addAssociatedPart(part10);//add eight
        prod2.addAssociatedPart(part8);
        Product prod3 = new Product(203, "Model 3", 84_490, 29, 1, 1000);
        prod3.addAssociatedPart(part5);
        prod3.addAssociatedPart(part9);
        prod3.addAssociatedPart(part6);
        prod3.addAssociatedPart(part1);

        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);
        Inventory.addProduct(prod3);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);
        Inventory.addPart(part7);
        Inventory.addPart(part8);
        Inventory.addPart(part9);
        Inventory.addPart(part10);
        launch(args);
    }
}
