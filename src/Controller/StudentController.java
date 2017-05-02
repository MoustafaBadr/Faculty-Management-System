
package Controller;

import database.Dataconnection;
import Model.STUDENT;
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
public class StudentController implements Initializable {
   
     
    PreparedStatement pst = null;
    ResultSet rs = null;
    ObservableList<String> studentlist=FXCollections.observableArrayList("first-Name","graduation_year");
    
    
    
    
    
    @FXML
    private JFXComboBox StudentBox;
    @FXML
    private JFXButton Retrieve; 
    @FXML
    private JFXTextField Studentchoise;
    @FXML
    private TableView<STUDENT> studenttable;
    @FXML
    private TableColumn<STUDENT,String> fname;
    @FXML
    private TableColumn<STUDENT,String> lname;
    @FXML
    private TableColumn<STUDENT,String> addresscl;
    @FXML
    private TableColumn<STUDENT,String> statecl;
    @FXML
    private TableColumn<STUDENT,String> phonenumbercl;
    @FXML
    private TableColumn<STUDENT,String> emailcl;
    @FXML
    private TableColumn<STUDENT,String> graduationyearcl;
    
    ObservableList<STUDENT> observstudentlist;
    private Dataconnection dc;
   

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StudentBox.setValue("first-Name");
        StudentBox.setItems(studentlist);
    

    dc=new Dataconnection();
    
    }
    
    
    public void loaddatafromstudent(ActionEvent event)throws IOException,SQLException{
    Connection con=dc.mkDataBase();
    observstudentlist=FXCollections.observableArrayList();
   
      if(Studentchoise.getText().equals("")){
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
      }
      else if (StudentBox.getValue().equals("first-Name")){
    String fnsf=Studentchoise.getText();
    String selectsqlstu = "SELECT * FROM student where first_name=?";
     pst = con.prepareStatement(selectsqlstu);
            pst.setString(1, fnsf);
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
                   observstudentlist.add(new STUDENT(rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(6),rs.getString(8),rs.getString(9),rs.getString(10)));

            }while (rs.next());
           }
           
            
          
            fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            statecl.setCellValueFactory(new PropertyValueFactory<>("address"));
            addresscl.setCellValueFactory(new PropertyValueFactory<>("state"));
            phonenumbercl.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
            emailcl.setCellValueFactory(new PropertyValueFactory<>("email"));
            graduationyearcl.setCellValueFactory(new PropertyValueFactory<>("graduationyear"));

            studenttable.setItems(null);
            studenttable.setItems(observstudentlist);
    }else 
        if(StudentBox.getValue().equals("graduation_year")){
            String fnsg=Studentchoise.getText();
    String selectsqlstu = "SELECT * FROM student where graduation_year=?";
     pst = con.prepareStatement(selectsqlstu);
            pst.setString(1, fnsg);
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
                 observstudentlist.add(new STUDENT(rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(6),rs.getString(8),rs.getString(9),rs.getString(10)));

            }while (rs.next());
           }
            
           
            fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            statecl.setCellValueFactory(new PropertyValueFactory<>("address"));
            addresscl.setCellValueFactory(new PropertyValueFactory<>("state"));
            phonenumbercl.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
            emailcl.setCellValueFactory(new PropertyValueFactory<>("email"));
            graduationyearcl.setCellValueFactory(new PropertyValueFactory<>("graduationyear"));

            studenttable.setItems(null);
            studenttable.setItems(observstudentlist);
        
        
        
        
        
        
        
        }
    }
    
    
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
