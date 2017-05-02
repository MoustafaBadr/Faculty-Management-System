package Controller;

import database.Dataconnection;
import Model.StudentData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Mikhail
 */
public class IbrahimMainController implements Initializable {

    String STUDENT_ID = null, COURSE_ID = null;
    Connection con = Dataconnection.mkDataBase();
    PreparedStatement pst = null;
    public TextField fNameTF, lNameTF, addressTF, cityTF, stateTF, emailTF, countryTF, phoneNumberTF, graduationTF;
    public Button saveBtn, resetBtn, backBtn;
    private StudentData em;
    @FXML
    private TableView<StudentData> table;
    @FXML
    private TableColumn<StudentData, String> first_name;
    @FXML
    private TableColumn<StudentData, String> last_name;
    @FXML
    private TableColumn<StudentData, String> address;
    @FXML
    private TableColumn<StudentData, String> city;
    @FXML
    private TableColumn<StudentData, String> state;
    @FXML
    private TableColumn<StudentData, String> country;
    @FXML
    private TableColumn<StudentData, String> phone_num;
    @FXML
    private TableColumn<StudentData, String> email;
    @FXML
    private TableColumn<StudentData, String> graduation_year;
    @FXML
    private TableColumn<StudentData, String> student_id;

    private ObservableList<StudentData> list;

    public void employee(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/IbrahimEmployee.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void back2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/MainFrame.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

//    public void checkerNULLValue (Object obj) throws NullPointerException{
//        if (obj == null) {
//            throw new NullPointerException("All data must f");
//        }
//    }
    @FXML
    public void saveData() throws SQLException {

        String firstName = null, lastName = null, address = null, city = null, state = null, email = null, country = null, phoneNumber = null, graduationYear = null;
        Integer phoneNmuberInt = null;
        try {
            // last and first name 
            firstName = fNameTF.getText();
            //checkerNULLValue(firstName);
            lastName = lNameTF.getText();
            //checkerNULLValue(lastName);
            //address
            address = addressTF.getText();
            // checkerNULLValue(address);
            //city
            city = cityTF.getText();
            //checkerNULLValue(city);
            //state
            state = stateTF.getText();
            // checkerNULLValue(state);
            //email
            email = emailTF.getText();
            try {
                for (int i = 1; i < email.length(); i++) {
                    if (!email.contains("@")) {
                        throw new NullPointerException("email not valid");
                    }
                }
            } catch (Exception ex) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("email not valid").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                emailTF.clear();
                throw new Exception("");
            }
            // checkerNULLValue(email);
            //country
            country = countryTF.getText();
            // checkerNULLValue(country);
            //phone number 
            phoneNumber = phoneNumberTF.getText();
            //  checkerNULLValue(phoneNumber);
            try {

            } catch (Exception e) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("phone number field must contained number values only").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                emailTF.clear();
            }

            try {
                phoneNmuberInt = Integer.parseInt(phoneNumber);
            } catch (NumberFormatException ex) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("phone number field must contained number values only").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                phoneNumberTF.clear();
            }
            // graduation year 
            graduationYear = graduationTF.getText();
            //  checkerNULLValue(graduationYear);
            try {
                phoneNmuberInt = Integer.parseInt(graduationYear);
            } catch (NumberFormatException ex) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("graduation field must contained number values only").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                graduationTF.clear();
            }// sql statement insert

            try {

                ResultSet rs = con.createStatement().executeQuery("Select email From student where email='" + email + "' ");

                if (rs.next()) {
                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("Student is already exist").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                    
                    
                    
                } else {

                    String Sql = "insert into student (first_name, last_name, address, city, state, country, phone_num, email, graduation_year) values ('" + firstName + "' ,'" + lastName + "' ,'" + address + "','" + city + "','" + state + "','" + country + "','" + phoneNumber + "','" + email + "','" + graduationYear + "')";
                    pst = con.prepareStatement(Sql);
                    pst.execute();
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } catch (NullPointerException es) {
            Notifications notificationBuildeer = Notifications.create().
                    title("error").
                    text("All Fields is Required").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT);
            notificationBuildeer.showWarning();
        } catch (Exception ex) {
            Notifications notificationBuildeer = Notifications.create().
                    title("error").
                    text("Can't add Student data something went wrong").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT);
            notificationBuildeer.showError();
        }

        try {
            retrieve(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(IbrahimMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void retrieve(ActionEvent event) throws SQLException {
        System.out.println("it's Working");
        list = FXCollections.observableArrayList();

        ResultSet rs = con.createStatement().executeQuery("select * from student");

        list.clear();
        while (rs.next()) {
            list.add(new StudentData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
        }

        student_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        graduation_year.setCellValueFactory(new PropertyValueFactory<>("graduation_year"));

        table.setItems(null);
        table.setItems(list);

    }

    @FXML
    public void updatestudent(ActionEvent event) throws SQLException {
        try {
            if (table.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("you should selcect a row ").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = table.getSelectionModel().getSelectedIndex();

                String sql2 = "update student set first_name='" + fNameTF.getText() + "'"
                        + ",last_name='" + lNameTF.getText()
                        + "',address='" + addressTF.getText()
                        + "',city='" + cityTF.getText()
                        + "',state='" + stateTF.getText()
                        + "',country='" + countryTF.getText()
                        + "' ,phone_num='" + phoneNumberTF.getText()
                        + "',email='" + emailTF.getText()
                        + "',graduation_year='" + graduationTF.getText() + "'"
                        + "  where student_id='" + list.get(selectedItem).student_id().getValue() + "'";

                pst = con.prepareStatement(sql2);
                pst.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("The Document has been updated").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("update Noti ");
                        });
                notificationBuildeer.showConfirm();
                retrieve(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void setTextFieldData() {
        this.fNameTF.setText(em != null ? em.first_name().getValue() : "");
        this.lNameTF.setText(em != null ? em.last_name().getValue() : "");
        this.addressTF.setText(em != null ? em.address().getValue() : "");
        this.cityTF.setText(em != null ? em.city().getValue() : "");
        this.stateTF.setText(em != null ? em.state().getValue() : "");
        this.countryTF.setText(em != null ? em.country().getValue() : "");
        this.phoneNumberTF.setText(em != null ? em.phone_num().getValue() : "");
        this.emailTF.setText(em != null ? em.email().getValue() : "");
        this.graduationTF.setText(em != null ? em.graduation_year().getValue() : "");
    }

    @FXML
    public void resetfields(ActionEvent event) {
        fNameTF.clear();
        lNameTF.clear();
        addressTF.clear();
        cityTF.clear();
        stateTF.clear();
        countryTF.clear();
        phoneNumberTF.clear();
        emailTF.clear();
        graduationTF.clear();
    }

    @FXML
    public void delete(ActionEvent event) {
        try {
            if (table.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("you should selcect a row ").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = table.getSelectionModel().getSelectedIndex();

                String sql2 = "delete from student where student_id='" + list.get(selectedItem).student_id().getValue() + "'";
                pst = con.prepareStatement(sql2);
                pst.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("The Document has been deleted").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("delete Noti");
                        });
                notificationBuildeer.showConfirm();
                retrieve(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            retrieve(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(IbrahimMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            retrieve(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(IbrahimMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent t) {
                em = table.getSelectionModel().getSelectedItem() != null ? table.getSelectionModel().getSelectedItem() : null;
                if (em != null) {
                    setTextFieldData();
                }
            }
        });

    }

}
