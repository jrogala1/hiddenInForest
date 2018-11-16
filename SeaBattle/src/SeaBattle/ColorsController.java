package SeaBattle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class ColorsController {

    @FXML
    private Button closeWindow;

    public void newWindow() throws Exception {
        try {
            //System.out.println("test");
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Seabattle_colors.fxml"));
            Parent colorroot = (Parent) fxmlloader.load();
            Stage colorStage = new Stage();
            colorStage.setTitle("Colors");
            colorStage.setScene(new Scene(colorroot,318,345));
            colorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }
}
