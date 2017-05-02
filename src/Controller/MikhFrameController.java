package Controller;

import database.Dataconnection;
import Model.person;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;

public class MikhFrameController implements Initializable {

    static int x;
    Connection con = Dataconnection.mkDataBase();
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private Label label;
    @FXML
    private TextField n;
    @FXML
    private TextField c;
    @FXML
    private ComboBox d;
    @FXML
    private TableView<person> table;
    @FXML
    private TableColumn<person, String> name;
    @FXML
    private TableColumn<person, Integer> cost;
    @FXML
    private TableColumn<person, String> dep;
    @FXML
    private TableColumn<person, Integer> id;
    private ObservableList<person> list;
    private person pe;

    public MikhFrameController() {

    }

    @FXML
    void department_name() {

        d.getItems().removeAll(d.getItems());
        d.getItems().addAll("cs", "is", "it", "or");

    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        d.getSelectionModel().select("cs");

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent t) {
                pe = table.getSelectionModel().getSelectedItem() != null ? table.getSelectionModel().getSelectedItem() : null;
                if (pe != null) {
                    setTextFieldData();
                }
            }
        });
        list = FXCollections.observableArrayList();

        retrive();

    }

    @FXML

    public void back6(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View/MainFrame.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    void SAveButton() throws SQLException {

        if (d.getValue().equals("cs")) {
            x = 1;
        } else if (d.getValue().equals("or")) {
            x = 4;
        } else if (d.getValue().equals("it")) {
            x = 2;
        } else if (d.getValue().equals("is")) {
            x = 3;
        }
        try {
            if (n.getText().equals("")) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("course name is requied").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {
                            System.out.println("warning Noti ");
                        });
                notificationBuildeer.showWarning();

            }
//            ResultSet dep = con.createStatement().executeQuery("SELECT  department_id FROM courses where department_id = '" + x + "'");
////
//            ResultSet name = con.createStatement().executeQuery("SELECT  name FROM courses where name= '" + n.getText() + "' ");
////
//            if (name.getString(1) == n.getText() ) {
//                System.out.println("what are you doing");
//
//            }
        else if (!n.getText().equals("")) {
                String Sql = "insert into courses (department_id,name,cost) values ('" + x + "' ,'" + n.getText() + "' ,'" + c.getText() + "')";
                pst = con.prepareStatement(Sql);
                pst.execute();

                Notifications notificationBuildeer = Notifications.create().
                        title("Saved").
                        text("The Document is saved").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("saved Noti ");

                        });
                notificationBuildeer.showConfirm();
                ResultSet rs = con.createStatement().executeQuery("SELECT  c.name ,c.cost ,d.description,c.course_id FROM faculty.courses c inner join department d on (c.department_id=d.department_id)");
                list.clear();
                while (rs.next()) {

                    list.add(new person(rs.getString(3), rs.getString(1), rs.getString(2), rs.getInt(4)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dep.setCellValueFactory(new PropertyValueFactory<>("department_naem"));
        id.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        table.setItems(null);
        table.setItems(list);
    }

    @FXML
    void update() throws SQLException {
        if (d.getValue().equals("cs")) {
            x = 1;
        } else if (d.getValue().equals("or")) {
            x = 4;
        } else if (d.getValue().equals("it")) {
            x = 2;
        } else if (d.getValue().equals("is")) {
            x = 3;
        }
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
            } else if (!n.getText().equals("")) {
                int selectedItem = table.getSelectionModel().getSelectedIndex();

                String sql2 = "update courses set  department_id='" + x + "',name='" + n.getText() + "',cost='" + c.getText() + "'  where course_id='" + list.get(selectedItem).course_idpro().getValue() + "' ";
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
                ResultSet rs = con.createStatement().executeQuery("SELECT  c.name ,c.cost ,d.description,c.course_id FROM faculty.courses c inner join department d on (c.department_id=d.department_id)");
                list.clear();
                while (rs.next()) {

                    list.add(new person(rs.getString(3), rs.getString(1), rs.getString(2), rs.getInt(4)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dep.setCellValueFactory(new PropertyValueFactory<>("department_naem"));
        id.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        table.setItems(null);
        table.setItems(list);
    }

    @FXML
    void delete() throws SQLException {

        try {

            if (table.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("you shoud select a row").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else if (!n.getText().equals("")) {

                ResultSet rss = null;

                int selectedItem = table.getSelectionModel().getSelectedIndex();
//                System.out.println("row " + selectedItem);
//                System.out.println("row num " + list.get(selectedItem).DepartmentPro().getValue());
//                String sql2 = "delete from courses where name='" + list.get(selectedItem).namePro().getValue() + "' and department_id = '" + x + "'  ";
                String sql2 = "delete from courses where course_id='" + list.get(selectedItem).course_idpro().getValue() + "' ";

                pst = con.prepareStatement(sql2);
                pst.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("deleted").
                        text("The Document has been deleted").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("delete Noti ");

                        });
                notificationBuildeer.showConfirm();
                ResultSet rs = con.createStatement().executeQuery("SELECT  c.name ,c.cost ,d.description,c.course_id FROM faculty.courses c inner join department d on (c.department_id=d.department_id)");
                list.clear();
                while (rs.next()) {

                    list.add(new person(rs.getString(3), rs.getString(1), rs.getString(2), rs.getInt(4)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dep.setCellValueFactory(new PropertyValueFactory<>("department_naem"));
        id.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        table.setItems(null);
        table.setItems(list);
    }

    public void retrive() {
        try {

            ResultSet rs = con.createStatement().executeQuery("SELECT  c.name ,c.cost ,d.description,c.course_id FROM faculty.courses c inner join department d on (c.department_id=d.department_id)");
            list.clear();

            while (rs.next()) {

                list.add(new person(rs.getString(3), rs.getString(1), rs.getString(2), rs.getInt(4)));
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dep.setCellValueFactory(new PropertyValueFactory<>("department_naem"));
        id.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        table.setItems(null);
        table.setItems(list);
    }

    private void setTextFieldData() {
        this.n.setText(pe != null ? pe.namePro().getValue() : "");
        this.c.setText(pe != null ? pe.CostPro().getValue() : "");
        this.d.setValue(pe != null ? pe.DepartmentPro().getValue() : "cs");
    }
}
