package Controller;

import Model.CORSE;
import database.Dataconnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mostafa
 */
public class CorsController implements Initializable {

    PreparedStatement pst = null;
    ResultSet rs = null;
    ObservableList<String> corseslist = FXCollections.observableArrayList("cost", "name");

    @FXML
    private JFXButton Retrieve;

    @FXML
    private JFXComboBox CorsesBox;

    @FXML
    private JFXTextField Corsechoise;

    @FXML
    private TableView<CORSE> corsetable;
    @FXML
    private TableColumn<CORSE, String> d_name;
    @FXML
    private TableColumn<CORSE, String> NAME;
    @FXML
    private TableColumn<CORSE, String> COST;

//initialize an ObservableList for table
    ObservableList<CORSE> observcorselist;
    private Dataconnection dc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CorsesBox.setValue("name");
        CorsesBox.setItems(corseslist);

        dc = new Dataconnection();

    }

    @FXML
    public void loaddatafromDB(ActionEvent event) throws IOException, SQLException {
        Connection con = dc.mkDataBase();
        observcorselist = FXCollections.observableArrayList();
        
        if(Corsechoise.getText().equals("")){
        Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("Your Choise is requied").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {
                            System.out.println("warning Noti ");
                        });
                notificationBuildeer.showWarning();
        } else if (CorsesBox.getValue().equals("name")) {
            String n = Corsechoise.getText();
            String selectsql = "SELECT c.name ,c.cost ,d.description "
                    + "FROM faculty.courses c inner join department d "
                    + "on (c.department_id=d.department_id)"
                    + "AND c.name=?";

            pst = con.prepareStatement(selectsql);
            pst.setString(1, n);
            rs = pst.executeQuery();
           if (!(rs.next())){
           
            Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("Not Found").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {
                            System.out.println("warning Noti ");
                        });
              notificationBuildeer.showWarning();
           }
           else {
               do {
                   
                observcorselist.add(new CORSE(rs.getString(3), rs.getString(1), rs.getString(2)));
                
            }while (rs.next());
           }
           
            
            d_name.setCellValueFactory(new PropertyValueFactory<>("departmentname"));
            NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
            COST.setCellValueFactory(new PropertyValueFactory<>("cost"));

            corsetable.setItems(null);
            corsetable.setItems(observcorselist);
        } else if (CorsesBox.getValue().equals("cost")) {
            String C = Corsechoise.getText();
            String selectsql = "SELECT c.name ,c.cost ,d.description "
                    + "FROM faculty.courses c inner join department d "
                    + "on (c.department_id=d.department_id)"
                    + "AND c.cost=?";

            pst = con.prepareStatement(selectsql);
            pst.setString(1, C);
            rs = pst.executeQuery();
            
            if (!(rs.next())){
           
            Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("Not Found").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {
                            System.out.println("warning Noti ");
                        });
              notificationBuildeer.showWarning();
           }else{
            
    do {
                observcorselist.add(new CORSE(rs.getString(3), rs.getString(1), rs.getString(2)));
            }while(rs.next());
    }
            
        

            d_name.setCellValueFactory(new PropertyValueFactory<>("departmentname"));
            NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
            COST.setCellValueFactory(new PropertyValueFactory<>("cost"));

            corsetable.setItems(null);
            corsetable.setItems(observcorselist);
        }

    }

    @FXML
    public void back6(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/MainRetrieve.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
