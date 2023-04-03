package com.mycompany.miniprojet_glovo;

import com.mycompany.Model.DBcnx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/Login.fxml"));
        //scene = new Scene(fxmlLoader.load(), 640, 480);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*scene = new Scene(loadFXML("Login"), 640, 480);
        stage.setScene(scene);
        stage.show();*/
    }

    /*static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

 private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
*/
    public static void main(String[] args) throws SQLException {
        
       launch();
               
    /* Connection con = DBcnx.getConnection() ;
      if(con == null)
      { 
          System.out.println("connection failed");
      }else 
      {
          System.out.println("connection success");
      }*/
    }

}