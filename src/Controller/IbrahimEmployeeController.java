
package Controller;

import database.Dataconnection;
import Model.EmployeeData;
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

public class IbrahimEmployeeController implements Initializable {

    @FXML
    private TableView<EmployeeData> table;
    @FXML
    private TableColumn<EmployeeData, String> first_name;
    @FXML
    private TableColumn<EmployeeData, String> last_name;
    @FXML
    private TableColumn<EmployeeData, String> state;
    @FXML
    private TableColumn<EmployeeData, String> address;
    @FXML
    private TableColumn<EmployeeData, String> city;
    @FXML
    private TableColumn<EmployeeData, String> phone_num;
    @FXML
    private TableColumn<EmployeeData, String> email;
    @FXML
    private TableColumn<EmployeeData, String> id;
    @FXML
    private TableColumn<EmployeeData, String> salary;
    private ObservableList<EmployeeData> list;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    EmployeeData em;

    Connection con = Dataconnection.mkDataBase();
    PreparedStatement pst = null;
    public TextField fNameTF, lNameTF, addressTF, cityTF, stateTF, postalTF, phoneNumberTF, emailTF, salaryTF;
    public Button saveBtn, resetBtn, backBtn;

    public void back(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/MainFrame.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void stud(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/IbrahimMain.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void checkerNULLValue(Object obj) {
        if (obj == null) {
            throw new NullPointerException("All data must f");
        }
    }

    @FXML
    public void saveData() throws SQLException {
        String firstName = null, lastName = null, address = null, city = null, state = null, phoneNumber = null, email = null, salary = null;
        Integer salaryInt = null, phoneNumberInt = null;

        try {
            firstName = fNameTF.getText();
            checkerNULLValue(firstName);
            lastName = lNameTF.getText();
            checkerNULLValue(lastName);
            address = addressTF.getText();
            checkerNULLValue(address);
            city = cityTF.getText();
            checkerNULLValue(city);
            state = stateTF.getText();
            checkerNULLValue(state);
            //phone number 
            phoneNumber = phoneNumberTF.getText();
            checkerNULLValue(phoneNumber);
            try {
                phoneNumberInt = Integer.parseInt(phoneNumber);
            } catch (NumberFormatException ex) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("phone number field must contained number values only").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                phoneNumberTF.clear();
                throw new Exception();
            }
            // email 
            email = emailTF.getText();
            checkerNULLValue(email);
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
            // salary
            salary = salaryTF.getText();
            checkerNULLValue(salary);
            try {
                salaryInt = Integer.parseInt(salary);
            } catch (NumberFormatException ex) {
                Notifications notificationBuildeer = Notifications.create().
                        title("error").
                        text("salary field must contained number values only").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT);
                notificationBuildeer.showWarning();
                salaryTF.clear();
                throw new Exception("can't add employee");
            }

            try {
                ResultSet rs = con.createStatement().executeQuery("Select email From employees where email='" + email + "' ");

                if (rs.next()) {
                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("emplyee is already exist").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                } else {
                    String Sql = "insert into employees ( first_name, last_name, state, address, city, phone_num, email, salary, department_id) values ('" + firstName + "' ,'" + lastName + "' ,'" + state + "','" + address + "','" + city + "','" + phoneNumber + "','" + email + "','" + salary + "','" + 1 + "')";
                    pst = con.prepareStatement(Sql);
                    pst.execute();

                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("another emplyee is added").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showInformation();
                    retrieve(new ActionEvent());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new Exception();
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

    }

    @FXML
    public void resetFields() {

        fNameTF.clear();
        lNameTF.clear();
        addressTF.clear();
        cityTF.clear();
        stateTF.clear();
        phoneNumberTF.clear();
        salaryTF.clear();
        emailTF.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            retrieve(new ActionEvent());
        } catch (SQLException ex) {

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

    public void retrieve(ActionEvent event) throws SQLException {
        System.out.println("it's Working");
        list = FXCollections.observableArrayList();
        ResultSet rs = con.createStatement().executeQuery("Select * From employees");
        list.clear();
        while (rs.next()) {
            list.add(new EmployeeData(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(1)));
        }

        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        id.setCellValueFactory(new PropertyValueFactory<>("Employee_id"));
        table.setItems(null);
        table.setItems(list);

    }

    @FXML
    public void up() {
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

                String sql2 = "update employees set first_name='" + fNameTF.getText() + "'"
                        + ",last_name='" + lNameTF.getText() + "',state='" + stateTF.getText() + "'"
                        + ",address='" + addressTF.getText() + "',city='" + cityTF.getText() + "' "
                        + " ,phone_num='" + phoneNumberTF.getText() + "'  "
                        + ",email='" + emailTF.getText() + "'  ,salary='" + salaryTF.getText() + "'"
                        + "  where employee_id='" + list.get(selectedItem).Employee_idpro().getValue() + "' ";

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

    @FXML
    public void del() {
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

                String sql2 = "delete from employees where employee_id='" + list.get(selectedItem).Employee_idpro().getValue() + "' ";

                pst = con.prepareStatement(sql2);
                pst.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("The Document has been deleted").
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
        this.phoneNumberTF.setText(em != null ? em.phone_num().getValue() : "");
        this.emailTF.setText(em != null ? em.email().getValue() : "");
        this.salaryTF.setText(em != null ? em.Salary().getValue() : "");
    }

}
