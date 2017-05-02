package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeData {

    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty state;
    private StringProperty address;
    private StringProperty city;
    private StringProperty phone_num;
    private StringProperty email;
    private StringProperty Salary;

    public StringProperty first_name() {
        return first_name;
    }

    public StringProperty last_name() {
        return last_name;
    }

    public StringProperty state() {
        return state;
    }

    public StringProperty address() {
        return address;
    }

    public StringProperty city() {
        return city;
    }

    public StringProperty phone_num() {
        return phone_num;
    }

    public StringProperty email() {
        return email;
    }

    public StringProperty Salary() {
        return Salary;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public void setAdress(String address) {
        this.address.set(address);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public void setSalary(String salary) {
        this.Salary.set(salary);
    }

    public void setPhone_num(String phone_num) {
        this.phone_num.set(phone_num);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    //---------- Get --------------
    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getState() {
        return state.get();
    }

    public String getSalary() {
        return Salary.get();
    }

    public String getPhone_num() {
        return phone_num.get();
    }

    public String getEmail() {
        return email.get();
    }
    private IntegerProperty Employee_id;

    public IntegerProperty Employee_idpro() {
        return Employee_id;
    }

    public int getEmployee_id() {
        return Employee_id.get();
    }

    public void setEmployee_id(int value) {
        Employee_id.set(value);
    }

    public EmployeeData(String first_name, String last_name, String state, String address, String city, String phone_num, String email, String salary, Integer Employee_id) {
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.state = new SimpleStringProperty(state);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.phone_num = new SimpleStringProperty(phone_num);
        this.email = new SimpleStringProperty(email);
        this.Salary = new SimpleStringProperty(salary);
        this.Employee_id = new SimpleIntegerProperty(Employee_id);

    }

}
