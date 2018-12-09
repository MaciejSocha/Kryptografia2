package sample;

import algorithm.Sha1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("dupa");
        String dupa = "dupa";
        Sha1 sha1 = new Sha1();
        String ans = sha1.hasz(dupa);
        System.out.println(ans);
        launch(args);
    }
}
