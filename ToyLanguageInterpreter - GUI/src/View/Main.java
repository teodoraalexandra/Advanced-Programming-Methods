package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("listView.fxml"));
            Scene scene = new Scene(root,400,1000);

            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("ISTMT Programs");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
