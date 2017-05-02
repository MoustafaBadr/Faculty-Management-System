package Controller;

import database.Dataconnection;
import Model.EMPLOYEE;
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
public class EmplController implements Initializable {
   
    
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    ObservableList<String> employeelist=FXCollections.observableArrayList("first_name","salary");
    
    @FXML
    private JFXComboBox EmployeeBox;
    
    @FXML
    private JFXTextField employeechoise;
    
    @FXML
    private TableView<EMPLOYEE> employeetable;
    @FXML
    private TableColumn<EMPLOYEE,String> f_name;
    @FXML
    private TableColumn<EMPLOYEE,String> l_name;
    @FXML
    private TableColumn<EMPLOYEE,String> statecl;
    @FXML
    private TableColumn<EMPLOYEE,String> addresscl;
    @FXML
    private TableColumn<EMPLOYEE,String> phonenumbercl;
    @FXML
    private TableColumn<EMPLOYEE,String> emailcl;
    @FXML
    private TableColumn<EMPLOYEE,String> salarycl;
    @FXML
    private TableColumn<EMPLOYEE,String> deartmentname;
    
     ObservableList<EMPLOYEE> observemployeelist;
    private Dataconnection dc;
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
          
     EmployeeBox.setValue("first_name");
     EmployeeBox.setItems(employeelist);
      dc=new Dataconnection();
        
        
    }    
    
    public void loaddatafromemployee(ActionEvent event)throws IOException,SQLException{
    Connection con=dc.mkDataBase();
    observemployeelist=FXCollections.observableArrayList();
    
    if(employeechoise.getText().equals("")){
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
    }else if(EmployeeBox.getValue().equals("first_name")){
     String fn=employeechoise.getText();
      String selectsqlemp = "SELECT e.first_name,e.last_name,e.state,e.address,e.phone_num,e.email,e.salary,d.description"
              + " FROM faculty.employees e inner join department d  "
              + "on (e.department_id=d.department_id)"
              + "AND e.first_name=?";
      pst = con.prepareStatement(selectsqlemp);
            pst.setString(1, fn);
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
             do{
             observemployeelist.add(new EMPLOYEE(rs.getString(1), rs.getString(2), rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
             }while(rs.next());
             
             }
            
            
          

            f_name.setCellValueFactory(new PropertyValueFactory<>("employeefirstname"));
            l_name.setCellValueFactory(new PropertyValueFactory<>("employeelastname"));
            statecl.setCellValueFactory(new PropertyValueFactory<>("employeestate"));
            addresscl.setCellValueFactory(new PropertyValueFactory<>("employeeaddress"));
            phonenumbercl.setCellValueFactory(new PropertyValueFactory<>("employeephonenumber"));
            emailcl.setCellValueFactory(new PropertyValueFactory<>("employeeemail"));
            salarycl.setCellValueFactory(new PropertyValueFactory<>("employeesalary"));
            deartmentname.setCellValueFactory(new PropertyValueFactory<>("employeedepartment"));

            employeetable.setItems(null);
            employeetable.setItems(observemployeelist);
                   
    
    }else if (EmployeeBox.getValue().equals("salary")){
    
      String fns=employeechoise.getText();
      String selectsqlemp = "SELECT e.first_name,e.last_name,e.state,e.address,e.phone_num,e.email,e.salary,d.description"
              + " FROM faculty.employees e inner join department d  "
              + "on (e.department_id=d.department_id)"
              + "AND e.salary=?";
      pst = con.prepareStatement(selectsqlemp);
            pst.setString(1, fns);
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
             do{
              observemployeelist.add(new EMPLOYEE(rs.getString(1), rs.getString(2), rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
             }while(rs.next());
             
             }
            
           

            f_name.setCellValueFactory(new PropertyValueFactory<>("employeefirstname"));
            l_name.setCellValueFactory(new PropertyValueFactory<>("employeelastname"));
            statecl.setCellValueFactory(new PropertyValueFactory<>("employeestate"));
            addresscl.setCellValueFactory(new PropertyValueFactory<>("employeeaddress"));
            phonenumbercl.setCellValueFactory(new PropertyValueFactory<>("employeephonenumber"));
            emailcl.setCellValueFactory(new PropertyValueFactory<>("employeeemail"));
            salarycl.setCellValueFactory(new PropertyValueFactory<>("employeesalary"));
            deartmentname.setCellValueFactory(new PropertyValueFactory<>("employeedepartment"));

            employeetable.setItems(null);
            employeetable.setItems(observemployeelist);
    
    
    
    
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
