package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class LoginController implements Initializable {

    String nam = "admin";
    String password = "admin";
    @FXML
    JFXTextField name;
    @FXML
    JFXPasswordField pass;

    public void handleButtonAction(ActionEvent event) throws IOException {

        if (!name.getText().equals(nam)) {
            Notifications notificationBuildeer = Notifications.create().
                    title("error").
                    text("enter the coorect name ").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
        } else if (!pass.getText().equals(password)) {
            Notifications notificationBuildeer = Notifications.create().
                    title("error").
                    text("enter the coorect password ").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();

        } else {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/MainFrame.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
